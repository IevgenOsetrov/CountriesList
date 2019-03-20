package com.dev.joks.countrieslist

import android.app.Application
import com.dev.joks.countrieslist.di.networkModule
import com.dev.joks.countrieslist.di.viewModelModule
import org.koin.standalone.StandAloneContext.startKoin

class CountriesListApp : Application() {

    companion object {
        lateinit var instance: CountriesListApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin(listOf(networkModule, viewModelModule))
    }
}