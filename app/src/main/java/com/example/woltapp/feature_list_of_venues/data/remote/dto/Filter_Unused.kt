package com.example.woltapp.feature_list_of_venues.data.remote.dto

data class Filter_Unused(
    val id: String,
    val name: String,
    val type: String,
    val valueUnuseds: List<com.example.woltapp.feature_list_of_venues.data.remote.dto.Value_Unused>
)