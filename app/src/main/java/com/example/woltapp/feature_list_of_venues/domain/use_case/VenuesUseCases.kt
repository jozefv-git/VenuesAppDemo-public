package com.example.woltapp.feature_list_of_venues.domain.use_case

data class VenuesUseCases(
    val getVenues: GetVenuesUseCase,
    val updateVenues: UpdateVenueUseCase,
    val deleteVenues: DeleteVenuesUseCase,
    val restoreVenues: RestoreVenuesUseCase
)
