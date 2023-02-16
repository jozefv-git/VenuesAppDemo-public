package com.example.woltapp.feature_list_of_venues.domain.repository

import com.example.woltapp.feature_list_of_venues.common.Resource
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow

interface CloseVenueRepository {
    fun getMarkerVenues(lat: Double, lon: Double): Flow<Resource<List<VenueModel>>>
    fun updateVenue(venueModel: VenueModel, isVenueFavorite: Boolean): Flow<List<VenueModel>>
    suspend fun deleteVenues(lat: Double, lon: Double)
    suspend fun addVenue(venue: VenueModel)
}