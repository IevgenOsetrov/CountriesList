package com.dev.joks.countrieslist.di

import com.dev.joks.countrieslist.screens.main.CountriesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { CountriesViewModel(get()) }
}