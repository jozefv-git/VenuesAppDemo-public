package com.example.woltapp.feature_list_of_venues.domain.use_case

import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import javax.inject.Inject

class RestoreVenuesUseCase @Inject constructor(
    private val repository: CloseVenueRepository
) {
    suspend operator fun invoke(venues: List<VenueModel>?) {
        venues?.forEach { venue ->
            repository.addVenue(venue)
        }
    }
}