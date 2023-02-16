package com.example.woltapp.feature_list_of_venues.di

import android.app.Application
import androidx.room.Room
import com.example.woltapp.feature_list_of_venues.data.local.VenueDatabase
import com.example.woltapp.feature_list_of_venues.data.remote.WoltApi
import com.example.woltapp.feature_list_of_venues.data.repository.CloseVenueRepositoryImplementation
import com.example.woltapp.feature_list_of_venues.domain.repository.CloseVenueRepository
import com.example.woltapp.feature_list_of_venues.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun venuesUseCases(repository: CloseVenueRepository): VenuesUseCases {
        return VenuesUseCases(
            getVenues = GetVenuesUseCase(repository),
            updateVenues = UpdateVenueUseCase(repository),
            deleteVenues = DeleteVenuesUseCase(repository),
            restoreVenues = RestoreVenuesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun closeVenueRepositoryProvide(api: WoltApi, database: VenueDatabase): CloseVenueRepository {
        return CloseVenueRepositoryImplementation(api, database)
    }

    @Provides
    @Singleton
    fun localDB(app: Application): VenueDatabase {
        return Room.databaseBuilder(
            app, VenueDatabase::class.java, "venues_db"
        ).build()
    }

    @Provides
    @Singleton
    fun woltApiProvide(): WoltApi {
        return Retrofit.Builder().baseUrl(WoltApi.DEFAULT_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(WoltApi::class.java)
    }


}