package com.example.woltapp.feature_list_of_venues.data.remote

import com.example.woltapp.feature_list_of_venues.data.remote.dto.VenuesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WoltApi {
    @GET("restaurants")
    suspend fun getVenues(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): VenuesDto

    companion object {
        const val DEFAULT_URL = "https://restaurant-api.wolt.com/v1/pages/"
    }
}