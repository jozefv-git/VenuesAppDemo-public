package com.example.woltapp.feature_list_of_venues.data.remote.dto

import com.example.woltapp.feature_list_of_venues.data.local.VenueEntity

//TODO: Don't delete unused classes - they will be used in future!
data class Item(
    val description: String,
    val filtering: FilteringX,
    val image: Image,
    val link: Link,
    val overlay: String,
    val quantity: Int,
    val quantity_str: String,
    val sorting: Sorting,
    val template: String,
    val title: String,
    val track_id: String,
    val venue: Venue
)

fun Item.toVenueEntity(lat: Double, lon: Double): VenueEntity {
    return VenueEntity(
        lat = lat,
        lon = lon,
        venueId = venue.id,
        venueName = venue.name,
        shorDescription = venue.short_description,
        imageUrl = image.url
    )
}