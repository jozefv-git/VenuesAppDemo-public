package com.example.woltapp.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Text(
    val heading: TextStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
    val itemHeading: TextUnit = 14.sp,
    val itemDescription: TextUnit = 12.sp,
    val infoText: TextUnit = 20.sp,
    val buttonText: TextUnit = 10.sp,
    val smileText: TextUnit  = 35.sp
)

data class Spacing(
    val default: Dp = 0.dp,
    val xSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp
)

val OwnText = compositionLocalOf { Text() }
val OwnSpacing = compositionLocalOf { Spacing() }