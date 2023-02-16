package com.example.woltapp.feature_list_of_venues.Presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenEvent
import com.example.woltapp.feature_list_of_venues.Presentation.MainScreenViewModel
import com.example.woltapp.ui.theme.OwnSpacing
import com.example.woltapp.ui.theme.OwnText

@Composable
fun OrderSection(
    viewModel: MainScreenViewModel,
    modifier: Modifier,
    isSelected: Boolean,
) {
    Column {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Liked first", fontSize = OwnText.current.buttonText)
            Switch(checked = isSelected, onCheckedChange = { checked ->
                if (checked) {
                    viewModel.onEvent(
                        MainScreenEvent.IsOnOrderFavoriteClick(isOrderFavorite = true)
                    )
                } else {
                    viewModel.onEvent(
                        MainScreenEvent.IsOnOrderFavoriteClick(isOrderFavorite = false)
                    )
                }
            })
        }
        Divider(modifier = Modifier.padding(bottom = OwnSpacing.current.small))
    }

}