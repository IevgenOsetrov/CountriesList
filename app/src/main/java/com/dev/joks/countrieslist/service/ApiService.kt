package com.dev.joks.countrieslist.service

import com.dev.joks.countrieslist.service.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getCountries(): Single<List<Country>>
}