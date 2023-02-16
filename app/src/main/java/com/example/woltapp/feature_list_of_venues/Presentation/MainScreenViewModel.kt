package com.example.woltapp.feature_list_of_venues.Presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.woltapp.feature_list_of_venues.common.Resource
import com.example.woltapp.feature_list_of_venues.domain.model.SelectedMarkerModel
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.feature_list_of_venues.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val venuesUseCases: VenuesUseCases
) : ViewModel() {
    private val _state = mutableStateOf(ScreenState())
    val state: State<ScreenState> = _state

    private var unsortedVenues: List<VenueModel>? = null

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnMarkerClick -> {
                venuesUseCases.getVenues(
                    event.lat,
                    event.lon
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = ScreenState(
                                venues = result.data,
                                selectedMarker = SelectedMarkerModel(lat = event.lat, event.lon)
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = ScreenState(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = ScreenState(
                                error = result.message ?: "Unexpected error occurred."
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is MainScreenEvent.OnLikeVenueClick -> {
                if (event.venueModel.favorite) {
                    _state.value =
                        _state.value.copy(
                            isVenueFavorite = false,
                            isOrderFavorite = false
                        )
                } else {
                    _state.value = _state.value.copy(
                        isVenueFavorite = true
                    )
                }
                venuesUseCases.updateVenues(
                    venueModel = event.venueModel,
                    isVenueFavorite = _state.value.isVenueFavorite
                ).onEach { data ->
                    _state.value = _state.value.copy(
                        venues = data,
                        isOrderFavorite = false
                    )
                }.launchIn(viewModelScope)
            }
            is MainScreenEvent.IsBottomSheetExpanded -> {
                if (event.expanded) {
                    _state.value = _state.value.copy(
                        isBottomSheetExpanded = true
                    )
                } else {
                    _state.value = _state.value.copy(
                        isBottomSheetExpanded =
                        false)
                }
            }
            is MainScreenEvent.OnDeleteVenuesClick -> {
                viewModelScope.launch {
                    venuesUseCases.deleteVenues(event.lat, event.lon)
                }
                venuesUseCases.getVenues(
                    lat = event.lat,
                    lon = event.lon,
                    isOrderFavorite = false
                ).onEach { result ->
                    unsortedVenues = result.data
                }.launchIn(viewModelScope)
                _state.value = ScreenState(
                    selectedMarker = SelectedMarkerModel(event.lat, event.lon),
                    areVenuesDeleted = true,
                )
            }
            is MainScreenEvent.OnRestoredVenuesClick -> {
                viewModelScope.launch {
                    venuesUseCases.restoreVenues(unsortedVenues)
                }
                _state.value = _state.value.copy(
                    areVenuesDeleted =
                    false,
                    venues = unsortedVenues,
                    isOrderFavorite = false
                )
            }
            is MainScreenEvent.IsSnackBarDismissed -> {
                _state.value = _state.value.copy(
                    isSnackBarDismissed = true
                )
            }
            is MainScreenEvent.IsOnOrderFavoriteClick -> {
                val markerPosition = _state.value.selectedMarker?.let {
                    SelectedMarkerModel(it.lat, it.lon)
                }
                if (markerPosition != null) {
                    venuesUseCases.getVenues(
                        lat = markerPosition.lat,
                        lon = markerPosition.lon,
                        isOrderFavorite = event.isOrderFavorite
                    ).onEach { result ->
                        if (!event.isOrderFavorite) {
                            unsortedVenues = result.data
                        }
                        if (result.data != null) {
                            _state.value = _state.value.copy(
                                venues = result.data,
                                selectedMarker = markerPosition,
                                isOrderFavorite = event.isOrderFavorite
                            )
                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}
