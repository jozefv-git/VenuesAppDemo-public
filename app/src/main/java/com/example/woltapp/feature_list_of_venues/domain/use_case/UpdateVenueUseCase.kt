package com.example.woltapp.feature_list_of_venues.domain.use_case

import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateVenueUseCase @Inject constructor(
    private val repository: CloseVenueRepository
) {
    operator fun invoke(
        venueModel: VenueModel, isVenueFavorite: Boolean
    ): Flow<List<VenueModel>> {
        return repository.updateVenue(venueModel = venueModel, isVenueFavorite = isVenueFavorite)
    }
}