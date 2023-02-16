package com.example.woltapp.feature_list_of_venues.Presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.woltapp.feature_list_of_venues.common.constants.DefaultMap
import com.example.woltapp.feature_list_of_venues.common.constants.VenuesMarkers
import com.example.woltapp.feature_list_of_venues.Presentation.components.BottomSheetContent
import com.example.woltapp.ui.theme.OwnSpacing
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {

        }
        BottomSheetScaffold(scaffoldState = bottomScaffoldState, sheetContent = {
            if (sheetState.isExpanded) {
                viewModel.onEvent(
                    MainScreenEvent.IsBottomSheetExpanded(true)
                )
            }
            if (sheetState.isCollapsed) {
                viewModel.onEvent(
                    MainScreenEvent.IsBottomSheetExpanded(false)
                )
            }
            BottomSheetContent(viewModel = viewModel, scaffoldState = scaffoldState)
        }, sheetPeekHeight = 48.dp, sheetShape = RoundedCornerShape(
            topStart = OwnSpacing.current.large, topEnd = OwnSpacing.current.large
        ), contentColor = Color.White, content = {
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
                    val markerState = rememberMarkerState(position = LatLng(marker.lat, marker.lon))
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
        })
    }
}