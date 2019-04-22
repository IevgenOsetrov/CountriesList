package com.dev.joks.countrieslist.repository

import com.dev.joks.countrieslist.service.model.Country
import kotlinx.coroutines.Deferred

interface Repository {
    fun getCountries(): Deferred<List<Country>>
}