package com.dev.joks.countrieslist.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.joks.countrieslist.R
import com.dev.joks.countrieslist.databinding.FragmentCountriesBinding
import com.dev.joks.countrieslist.screens.base.BaseFragment
import com.dev.joks.countrieslist.screens.details.COUNTRY
import com.dev.joks.countrieslist.screens.main.adapter.CountriesAdapter
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_countries.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_countries

    private lateinit var countriesBinding: FragmentCountriesBinding
    private lateinit var countriesAdapter: CountriesAdapter
    val countriesViewModel: CountriesViewModel by viewModel()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        ReactiveNetwork.observeNetworkConnectivity(activity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { connectivity ->
                if (connectivity.available()) {
                    countriesViewModel.getCountries()
                } else {
                    toast(R.string.error_check_internet_connection)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        countriesBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return countriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setHomeButtonEnabled(false)

        countriesAdapter = CountriesAdapter(context, mutableListOf())

        countriesViewModel.response.observe(this, Observer {
            countriesAdapter.clear()
            countriesAdapter.addItems(it)
        })

        countriesViewModel.error.observe(this, Observer {
            toast(it)
        })

        val linearLayoutManager = LinearLayoutManager(context)
        rvCountries.apply {
            layoutManager = linearLayoutManager
            adapter = countriesAdapter
        }

        countriesAdapter.onItemClick = { country ->
            val bundle = Bundle()
            bundle.putParcelable(COUNTRY, country)
            findNavController().navigate(R.id.countryDetailsFragment, bundle)
        }

        countriesBinding.viewModel = countriesViewModel
    }
}
