package com.example.woltapp.feature_list_of_venues.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>() : Resource<T>(data = null)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
}
