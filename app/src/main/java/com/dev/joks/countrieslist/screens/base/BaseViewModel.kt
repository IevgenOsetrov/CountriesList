package com.dev.joks.countrieslist.screens.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val progressVisibility: MutableLiveData<Int> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getCompositeDisposable(): CompositeDisposable {
        return compositeDisposable
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun showError(throwable: Throwable) {
        progressVisibility.value = View.GONE
    }
}