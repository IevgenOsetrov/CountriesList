package com.dev.joks.countrieslist.repository

import com.dev.joks.countrieslist.service.ApiService
import com.dev.joks.countrieslist.service.model.Country
import kotlinx.coroutines.Deferred

class RepositoryImpl(private val apiService: ApiService) : Repository {

    override fun getCountries(): Deferred<List<Country>> {
        return apiService.getCountries()
    }
}