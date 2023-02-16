package com.example.woltapp.feature_list_of_venues.data.remote.dto

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<com.example.woltapp.feature_list_of_venues.data.remote.dto.Value>
)