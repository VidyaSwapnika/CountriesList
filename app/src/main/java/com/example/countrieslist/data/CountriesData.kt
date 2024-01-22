package com.example.countrieslist.data

class CountriesData : ArrayList<CountriesDataItem>()

data class CountriesDataItem(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

data class Language(
    val code: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)