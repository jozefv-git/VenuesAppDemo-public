package com.example.woltapp.feature_list_of_venues.domain.use_case

import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import javax.inject.Inject

class DeleteVenuesUseCase @Inject constructor(private val repository: CloseVenueRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) {
        repository.deleteVenues(lat = lat, lon = lon)
    }
}