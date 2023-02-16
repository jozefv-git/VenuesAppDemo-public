package com.example.woltapp.feature_list_of_venues.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.woltapp.feature_list_of_venues.domain.model.VenueModel

@Entity
data class VenueEntity(
    @PrimaryKey val id: Int? = null,
    val lat: Double,
    val lon: Double,
    val venueId: String,
    val venueName: String,
    val shorDescription: String,
    val imageUrl: String,
    val favorite: Boolean = false
)

fun VenueEntity.toVenueModel(): VenueModel {
    return VenueModel(
        lat = lat,
        lon = lon,
        venueId = venueId,
        venueName = venueName,
        shorDescription = shorDescription,
        imageUrl = imageUrl,
        favorite = favorite
    )
}
