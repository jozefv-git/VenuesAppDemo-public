package com.example.woltapp.feature_list_of_venues.data.remote.dto

data class Link(
    val target: String,
    val target_sort: String,
    val target_title: String,
    val title: String,
    val type: String,
    val venue_mainimage_blurhash: String
)