package com.example.countrieslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrieslist.data.CountriesDataItem
import com.example.countrieslist.databinding.CountryListItemBinding

class CountryListAdapter(val countryList: List<CountriesDataItem>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryListItemBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.binding.tvCountryNameAndRegion.text = country.name +" , "+ country.region
        holder.binding.tvCountryCode.text = country.code
        holder.binding.tvCountryCapital.text = country.capital
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

}

class CountryViewHolder(val binding: CountryListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}