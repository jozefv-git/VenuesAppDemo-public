package com.example.woltapp.feature_list_of_venues.domain.use_case

import android.util.Log
import com.example.woltapp.feature_list_of_venues.common.Resource
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVenuesUseCase @Inject constructor(
    private val repository: CloseVenueRepository
) {
    operator fun invoke(
        lat: Double, lon: Double, isOrderFavorite: Boolean = false
    ): Flow<Resource<List<VenueModel>>> {
        return if (isOrderFavorite) {
            repository.getMarkerVenues(lat = lat, lon = lon).map {
                    Resource.Success(data = it.data?.sortedByDescending { data ->
                        data.favorite
                    })
                }
        } else {
            repository.getMarkerVenues(lat = lat, lon = lon)
        }
    }
}
