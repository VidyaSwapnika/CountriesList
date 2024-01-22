package com.example.countrieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countrieslist.adapter.CountryListAdapter
import com.example.countrieslist.databinding.ActivityCountryListBinding
import com.example.countrieslist.repository.CountryListRepository
import com.example.countrieslist.utils.CountryResult
import com.example.countrieslist.service.RetrofitService
import com.example.countrieslist.viewmodel.CountryListViewModel
import com.example.countrieslist.viewmodel.CountryListViewModelFactory

class CountryListActivity : AppCompatActivity() {

    lateinit var viewModel: CountryListViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var binding: ActivityCountryListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            CountryListViewModelFactory(CountryListRepository(retrofitService))
        )[CountryListViewModel::class.java]


        viewModel.getCountries()

        viewModel.countryData.observe(this, Observer { response ->
            when (response) {
                is CountryResult.Success -> {
                    response.data?.let {
                        val adapter = CountryListAdapter(it)
                        binding.recyclerview.adapter = adapter
                    }
                }
                is CountryResult.Error -> {
                    response.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }
            }

        })
    }
}