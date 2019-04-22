package com.dev.joks.countrieslist.screens.main

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dev.joks.countrieslist.repository.Repository
import com.dev.joks.countrieslist.screens.base.BaseViewModel
import com.dev.joks.countrieslist.service.model.Country
import com.dev.joks.countrieslist.utils.TAG
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: Repository) : BaseViewModel() {

    val response: MutableLiveData<List<Country>> = MutableLiveData()

    fun getCountries() {
        viewModelScope.launch {
            try {
                progressVisibility.value = View.VISIBLE
                val countries = repository.getCountries().await()
                response.value = countries
                progressVisibility.value = View.GONE
            } catch (ex: Exception) {
                Log.e(TAG, ex.message)
                progressVisibility.value = View.GONE
            }
        }
    }
}