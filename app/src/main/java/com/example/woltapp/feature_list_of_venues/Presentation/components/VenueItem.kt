package com.example.woltapp.feature_list_of_venues.Presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel
import com.example.woltapp.ui.theme.OwnSpacing
import com.example.woltapp.ui.theme.OwnText

@Composable
fun VenueItem(
    venue: VenueModel, likeVenueClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = venue.imageUrl),
            contentDescription = venue.venueName,
            modifier = Modifier.size(72.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(start = OwnSpacing.current.medium, end = OwnSpacing.current.medium)
        ) {
            Text(
                text = venue.venueName,
                fontSize = OwnText.current.itemHeading,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = venue.shorDescription,
                fontSize = OwnText.current.itemDescription,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = likeVenueClick) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Like venue",
                tint = if (venue.favorite) Color.Red else MaterialTheme.colors.onSurface
            )
        }
    }
}