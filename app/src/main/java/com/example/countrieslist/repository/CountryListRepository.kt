package com.example.countrieslist.repository

import com.example.countrieslist.service.RetrofitService

class CountryListRepository constructor(private val retrofitService: RetrofitService) {

     suspend fun getCountriesData() = retrofitService.getCountryData()

}