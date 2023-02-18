package com.example.woltapp.feature_list_of_venues.Presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenEvent
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenViewModel
import com.example.woltapp.feature_list_of_venues.Presentation.bottomSheet.bottomSheet.InfoTextColumn
import com.example.woltapp.ui.theme.OwnSpacing
import com.example.woltapp.ui.theme.OwnText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomSheetContent(
    viewModel: MainScreenViewModel = hiltViewModel(), scaffoldState: ScaffoldState
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = OwnSpacing.current.medium, end = OwnSpacing.current.medium)
        ) {
            BottomSheetHeading(
                height = 48.dp,
                viewModel = viewModel,
                scaffoldState = scaffoldState,
                venuesAreLoadingTitle = "Just a sec...",
                availableVenuesTitle = "Check venues!",
                availableVenuesExpandedTitle = "Your venues",
                venuesDeletedTitle = "Deleted!",
                nonAvailableVenuesTitle = "Click on marker!",
                errorTitle = "Error"
            )
            if (state.isLoading || state.error.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (state.isLoading) "Please wait, venues are fetching" else state.error,
                            fontSize = OwnText.current.infoText
                        )
                        if (state.isLoading) {
                            Spacer(modifier = Modifier.height(OwnSpacing.current.large))
                            CircularProgressIndicator()
                        }
                    }
                }
            }
            if (state.venues == null && !state.areVenuesDeleted && !state.isLoading) {
                InfoTextColumn(
                    modifier = Modifier.fillMaxSize(),
                    infoMessage = "Your results will be visible here"
                )
            }
            AnimatedContent(
                transitionSpec = {
                    fadeIn() + slideInVertically(initialOffsetY = { it }) with
                            fadeOut() + slideOutVertically(targetOffsetY = { -it })
                }, targetState = state.isBottomSheetExpanded,
                content = { isBottomSheetExpanded ->
                    if (isBottomSheetExpanded) {
                        Column() {
                            state.venues?.let {
                                OrderSection(
                                    viewModel = viewModel,
                                    modifier = Modifier.fillMaxWidth(),
                                    isSelected = state.isOrderFavorite
                                )
                            }
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (state.areVenuesDeleted) {
                                    if (state.isSnackBarDismissed) {
                                        item {
                                            Column(
                                                modifier = Modifier.fillParentMaxSize(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                TextButton(modifier = Modifier.fillMaxWidth(),
                                                    onClick = {
                                                        state.selectedMarker?.let { position ->
                                                            viewModel.onEvent(
                                                                MainScreenEvent.OnMarkerClick(
                                                                    position.lat, position.lon
                                                                )
                                                            )
                                                        }
                                                    }) {
                                                    Text(
                                                        text = ("Fetch new API data").uppercase(),
                                                        fontSize = OwnText.current.buttonText
                                                    )
                                                }
                                            }
                                        }
                                    } else {
                                        item {
                                            InfoTextColumn(
                                                modifier = Modifier.fillParentMaxSize(),
                                                infoMessage = ":-(",
                                                fontSize = OwnText.current.smileText
                                            )
                                        }
                                    }
                                }
                                state.venues?.let { venues ->
                                    items(venues) {
                                        VenueItem(venue = it, likeVenueClick = {
                                            viewModel.onEvent(
                                                MainScreenEvent.OnLikeVenueClick(it)
                                            )
                                        })
                                    }
                                }
                            }
                        }
                    }
                })
        }
    }
}