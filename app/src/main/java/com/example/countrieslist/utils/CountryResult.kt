package com.example.countrieslist.utils

sealed class CountryResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : CountryResult<T>(data)
    class Error<T>(message: String, data: T? = null) : CountryResult<T>(data, message)
}