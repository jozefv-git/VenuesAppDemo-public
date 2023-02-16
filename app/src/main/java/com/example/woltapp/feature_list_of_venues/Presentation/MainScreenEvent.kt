package com.example.woltapp.feature_list_of_venues.Presentation

import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel

sealed class MainScreenEvent {
    data class OnMarkerClick(val lat: Double, val lon: Double) : MainScreenEvent()
    data class OnLikeVenueClick(val venueModel: VenueModel) : MainScreenEvent()
    data class OnDeleteVenuesClick(val lat: Double, val lon: Double) : MainScreenEvent()
    data class IsBottomSheetExpanded(val expanded: Boolean) : MainScreenEvent()
    data class OnRestoredVenuesClick(val venues: List<VenueModel>?) : MainScreenEvent()
    data class IsOnOrderFavoriteClick(val isOrderFavorite: Boolean) : MainScreenEvent()
    object IsSnackBarDismissed : MainScreenEvent()
}
