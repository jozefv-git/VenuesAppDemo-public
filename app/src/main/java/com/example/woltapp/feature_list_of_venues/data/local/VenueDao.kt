package com.example.woltapp.feature_list_of_venues.data.local

import androidx.room.*


@Dao
interface VenueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVenue(venueEntity: VenueEntity)

    // If there is a same venue at different position and it is cached locally,
    // and user click it as favorite - update venue for all positions
    @Query("UPDATE venueentity SET favorite=:favorite WHERE venueName LIKE :venueName")
    suspend fun updateVenue(favorite: Boolean, venueName: String)

    @Query("DELETE FROM venueentity WHERE lat LIKE :lat AND lon LIKE :lon")
    suspend fun deleteVenues(lat: String, lon: String)

    @Query("SELECT * FROM venueentity WHERE lat LIKE :lat AND lon LIKE :lon")
    suspend fun getVenues(lat: String, lon: String): List<VenueEntity>

    @Query("SELECT * FROM venueentity WHERE venueId LIKE :venueId")
    suspend fun getVenue(venueId: String): VenueEntity
}