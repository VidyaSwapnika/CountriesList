package com.example.countrieslist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.data.CountriesData
import com.example.countrieslist.repository.CountryListRepository
import com.example.countrieslist.utils.CountryResult
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException


class CountryListViewModel constructor(private val repository: CountryListRepository) :
    ViewModel() {

    val countryData: MutableLiveData<CountryResult<CountriesData>> = MutableLiveData()

    fun getCountries() = viewModelScope.launch {
        fetchCountries()
    }

    private suspend fun fetchCountries() {
        try {
            val data = repository.getCountriesData()
            countryData.postValue(handleCountryDataResponse(data))
        } catch (t: Throwable) {
            when (t) {
                is IOException -> countryData.postValue(CountryResult.Error("Network Failure"))
                else -> countryData.postValue(CountryResult.Error("Any Failure"))
            }
        }
    }

    private fun handleCountryDataResponse(data: Response<CountriesData>): CountryResult<CountriesData> {
        if (data.isSuccessful) {
            data.body()?.let { resultResponse ->
                return CountryResult.Success(resultResponse)
            }
        }
        return CountryResult.Error(data.message())
    }


}