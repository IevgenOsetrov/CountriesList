package com.dev.joks.countrieslist

import android.app.Application
import com.dev.joks.countrieslist.service.AppApi

class CountriesListApp : Application() {

    companion object {
        lateinit var instance: CountriesListApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        AppApi.init()
    }
}