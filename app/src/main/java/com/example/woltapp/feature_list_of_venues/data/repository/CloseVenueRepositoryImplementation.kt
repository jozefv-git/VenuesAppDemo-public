package com.example.woltapp.feature_list_of_venues.data.repository

import com.example.woltapp.feature_list_of_venues.common.Resource
import com.example.woltapp.feature_list_of_venues.data.local.VenueDatabase
import com.example.woltapp.feature_list_of_venues.data.local.toVenueModel
import com.example.woltapp.feature_list_of_venues.data.remote.WoltApi
import com.example.woltapp.feature_list_of_venues.data.remote.dto.toVenueEntity
import com.example.woltapp.feature_list_of_venues.data.remote.dto.toVenueItemModel
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.feature_list_of_venues.domain.model.toVenueEntity
import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloseVenueRepositoryImplementation @Inject constructor(
    private val api: WoltApi, private val database: VenueDatabase
) : CloseVenueRepository {

    private val dao = database.dao

    override fun getMarkerVenues(
        lat: Double, lon: Double
    ): Flow<Resource<List<VenueModel>>> {
        return flow {
            val localVenues =
                dao.getVenues(lat = lat.toString(), lon = lon.toString()).map { it.toVenueModel() }
            val isDbEmpty = localVenues.isEmpty()
            // if DB is not empty finish flow here
            if (!isDbEmpty) {
                emit(Resource.Loading())
                emit(Resource.Success(data = localVenues))
                return@flow
            }
            // If db is empty, try to fetch data from the API
            val apiVenues = try {
                emit(Resource.Loading())
                api.getVenues(lat = lat.toString(), lon = lon.toString()).toVenueItemModel()
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "Unexpected error occurred.",
                    )
                )
                null
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Server is unreachable.",
                    )
                )
                null
            }
            // if api data are not null, slice and save it into the db
            apiVenues?.let { venues ->
                val reducedVenues = venues.items.slice(0..14)
                val updatedVenues = venues.copy(items = reducedVenues)
                updatedVenues.items.forEach { venue ->
                    val venueToSave = venue.toVenueEntity(lat = lat, lon = lon)
                    dao.addVenue(venueToSave)
                }
                val getLocalVenues = dao.getVenues(lat = lat.toString(), lon = lon.toString())
                    .map { it.toVenueModel() }
                emit(Resource.Success(data = getLocalVenues))
            }
        }
    }

    override fun updateVenue(
        venueModel: VenueModel, isVenueFavorite: Boolean
    ): Flow<List<VenueModel>> {
        return flow {
            dao.updateVenue(favorite = isVenueFavorite, venueName = venueModel.venueName)
            val updatedLocalValues =
                dao.getVenues(lat = venueModel.lat.toString(), lon = venueModel.lon.toString())
                    .map { it.toVenueModel() }
            emit(updatedLocalValues)
        }
    }

    override suspend fun deleteVenues(lat: Double, lon: Double) {
        return dao.deleteVenues(lat = lat.toString(), lon = lon.toString())
    }

    override suspend fun addVenue(venue: VenueModel) {
        dao.addVenue(venue.toVenueEntity())
    }
}