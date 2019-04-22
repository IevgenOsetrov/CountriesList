package com.dev.joks.countrieslist

import android.app.Application
import com.dev.joks.countrieslist.di.networkModule
import com.dev.joks.countrieslist.di.viewModelModule
import org.koin.android.ext.android.startKoin

class CountriesListApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this@CountriesListApp,
            modules = listOf(networkModule, viewModelModule)
        )
    }
}