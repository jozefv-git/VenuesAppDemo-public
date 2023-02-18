package com.example.woltapp.feature_list_of_venues.Presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenEvent
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenViewModel
import com.example.woltapp.feature_list_of_venues.common.constants.DefaultMap
import com.example.woltapp.feature_list_of_venues.common.constants.VenuesMarkers
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun Map(viewModel: MainScreenViewModel){
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    GoogleMap(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        uiSettings = uiSettings,
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    DefaultMap.defaultCameraCenterMap.lat,
                    DefaultMap.defaultCameraCenterMap.lon
                ), DefaultMap.defaultCameraZoom
            )
        }) {
        VenuesMarkers.getMarkers.forEach { marker ->
            val markerState =
                rememberMarkerState(position = LatLng(marker.lat, marker.lon))
            Marker(state = markerState,
                title = "Your selected position",
                snippet = "Latitude: ${marker.lat}, Longitude: ${marker.lon}",
                onClick = {
                    //markerState.showInfoWindow()
                    viewModel.onEvent(
                        MainScreenEvent.OnMarkerClick(
                            lat = it.position.latitude, lon = it.position.longitude
                        )
                    )
                    true
                })
        }
    }
}