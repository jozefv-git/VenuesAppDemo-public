package com.example.woltapp.feature_list_of_venues.Presentation.components.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import com.example.woltapp.ui.theme.OwnText

@Composable
fun InfoTextColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    infoMessage: String,
    fontSize: TextUnit = OwnText.current.infoText) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = infoMessage,
            fontSize = fontSize
        )
    }
}