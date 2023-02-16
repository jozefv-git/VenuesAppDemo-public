package com.example.woltapp.feature_list_of_venues.Presentation

import com.example.woltapp.feature_list_of_venues.domain.model.SelectedMarkerModel
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel

data class ScreenState(
    val selectedMarker: SelectedMarkerModel? = null,
    val venues: List<VenueModel>? = null,
    val isBottomSheetExpanded: Boolean = false,
    val isLoading: Boolean = false,
    val isVenueFavorite: Boolean = false,
    val isOrderFavorite: Boolean = false,
    val areVenuesDeleted: Boolean = false,
    val isSnackBarDismissed: Boolean = false,
    val error: String = ""
)
