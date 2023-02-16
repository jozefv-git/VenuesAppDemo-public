package com.example.woltapp.feature_list_of_venues.Presentation.components.bottomSheet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.woltapp.ui.theme.OwnText

@Composable
fun HeadingText(title: String,modifier: Modifier = Modifier){
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = title,
        style = OwnText.current.heading
    )
}