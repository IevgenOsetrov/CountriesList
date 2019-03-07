package com.dev.joks.countrieslist.screens.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.dev.joks.countrieslist.screens.base.BaseViewModel
import com.dev.joks.countrieslist.service.AppApi.apiService
import com.dev.joks.countrieslist.service.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesViewModel : BaseViewModel() {

    val response: MutableLiveData<List<Country>> = MutableLiveData()

    fun getCountries() {
        getCompositeDisposable().add(apiService.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onGetCountriesStart() }
            .subscribe(
                { response -> onGetCountriesSuccess(response) },
                { error -> showError(error) }
            ))
    }

    private fun onGetCountriesStart() {
        progressVisibility.value = View.VISIBLE
    }

    private fun onGetCountriesSuccess(data: List<Country>) {
        progressVisibility.value = View.GONE
        response.value = data
    }
}