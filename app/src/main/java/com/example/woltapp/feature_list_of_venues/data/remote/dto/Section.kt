package com.example.woltapp.feature_list_of_venues.data.remote.dto

data class Section(
    val items: List<Item>,
    val link: LinkX_Unused,
    val name: String,
    val template: String,
    val title: String
)