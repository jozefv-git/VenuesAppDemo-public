package com.example.woltapp.feature_list_of_venues.domain.model

import com.example.woltapp.feature_list_of_venues.data.local.VenueEntity


data class VenueModel(
    val lat: Double,
    val lon: Double,
    val venueId: String,
    val venueName: String,
    val shorDescription: String,
    val imageUrl: String,
    val favorite: Boolean = false
)

fun VenueModel.toVenueEntity(): VenueEntity {
    return VenueEntity(
        lat = lat,
        lon = lon,
        venueId = venueId,
        venueName = venueName,
        shorDescription = shorDescription,
        imageUrl = imageUrl,
        favorite = favorite
    )
}
