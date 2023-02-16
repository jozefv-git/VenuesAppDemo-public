package com.example.woltapp.feature_list_of_venues.data.remote.dto

import com.example.woltapp.feature_list_of_venues.domain.model.VenueItemModel


data class VenuesDto(
    val created: Created,
    val expires_in_seconds: Int,
    val filtering: Filtering,
    val name: String,
    val page_title: String,
    val sections: List<Section>,
    val show_large_title: Boolean,
    val show_map: Boolean,
    val sorting: SortingX,
    val track_id: String
)

fun VenuesDto.toVenueItemModel(): VenueItemModel {
    return VenueItemModel(
        items = sections[1].items
    )
}

