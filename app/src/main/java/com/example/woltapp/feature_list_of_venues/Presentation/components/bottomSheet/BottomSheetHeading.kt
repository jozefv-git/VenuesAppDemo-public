package com.example.woltapp.feature_list_of_venues.Presentation.components


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenEvent
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenViewModel
import com.example.woltapp.feature_list_of_venues.Presentation.components.bottomSheet.HeadingText
import com.example.woltapp.ui.theme.OwnSpacing
import com.example.woltapp.ui.theme.OwnText
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomSheetHeading(
    viewModel: MainScreenViewModel,
    scaffoldState: ScaffoldState,
    height: Dp,
    venuesAreLoadingTitle: String,
    availableVenuesTitle: String,
    availableVenuesExpandedTitle: String,
    venuesDeletedTitle: String,
    nonAvailableVenuesTitle: String,
    errorTitle: String
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val infiniteTransition = rememberInfiniteTransition()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = if (state.isBottomSheetExpanded && state.venues != null)
                    OwnSpacing.current.medium else OwnSpacing.current.default
            )
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (state.isBottomSheetExpanded && state.venues != null)
                Arrangement.SpaceBetween else Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (state.venues != null) {
                AnimatedContent(transitionSpec = {
                    fadeIn() + slideInHorizontally(initialOffsetX = { it }) with
                            fadeOut() + slideOutHorizontally(targetOffsetX = { -it })
                },
                    targetState = state.isBottomSheetExpanded,
                    content = { isBottomSheetExpanded ->
                        val yAxis by infiniteTransition.animateFloat(
                            initialValue = 0f,
                            targetValue = -5f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(500, easing = FastOutLinearInEasing),
                                repeatMode = RepeatMode.Reverse
                            )
                        )
                        HeadingText(
                            modifier = if (isBottomSheetExpanded) Modifier else Modifier.offset(y = yAxis.dp),
                            title = if (isBottomSheetExpanded) availableVenuesExpandedTitle else availableVenuesTitle
                        )
                    })
            } else { // Venues are null
                if (state.areVenuesDeleted) {
                    HeadingText(title = venuesDeletedTitle)
                } else {
                    if (state.error.isNotEmpty()) {
                        HeadingText(title = errorTitle)
                    } else {
                        HeadingText(title = if (state.isLoading) venuesAreLoadingTitle else nonAvailableVenuesTitle)
                    }
                }
            }
            AnimatedVisibility(
                visible = state.isBottomSheetExpanded && state.venues != null,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                if (state.isBottomSheetExpanded && state.venues != null) {
                    TextButton(onClick = {
                        state.selectedMarker?.let { position ->
                            viewModel.onEvent(
                                MainScreenEvent.OnDeleteVenuesClick(
                                    position.lat, position.lon
                                )
                            )
                            scope.launch {
                                val showSnackBar = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Local venues were deleted", actionLabel = "Undo"
                                )
                                if (showSnackBar == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(
                                        MainScreenEvent.OnRestoredVenuesClick(state.venues)
                                    )
                                }
                                if (showSnackBar == SnackbarResult.Dismissed) {
                                    viewModel.onEvent(
                                        MainScreenEvent.IsSnackBarDismissed
                                    )
                                }
                            }
                        }
                    }) {
                        Text(
                            text = ("Delete cache").uppercase(),
                            fontSize = OwnText.current.buttonText
                        )
                    }
                }
            }
        }
    }
}