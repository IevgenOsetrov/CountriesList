package com.dev.joks.countrieslist.screens.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.joks.countrieslist.screens.base.BaseFragment
import com.dev.joks.countrieslist.screens.main.MainActivity
import com.dev.joks.countrieslist.service.model.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_country_details.*


const val COUNTRY = "COUNTRY"

class CountryDetailsFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var country: Country

    override fun getLayoutRes(): Int = com.dev.joks.countrieslist.R.layout.fragment_country_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        country = arguments?.getParcelable(COUNTRY) as Country
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setHomeButtonEnabled(true)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        fillData(country)
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (country.location.isNotEmpty()) {
            val countryLocation = LatLng(country.location[0], country.location[1])
            googleMap?.addMarker(
                MarkerOptions().position(countryLocation)
            )
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(countryLocation, 5F))
        }
    }

    private fun fillData(country: Country) {
        tvName.text = String.format("%s (%s)", country.name, country.nativeName)
        tvCapital.text = country.capital
        tvPopulation.text = country.population.toString()
        tvArea.text = country.area.toString()
        tvRegion.text = country.region

        val stringBuilder = StringBuilder()
        country.languages.forEach {
            stringBuilder.append(it.name)
            if (it != country.languages.last()) {
                stringBuilder.append(", ")
            }
        }
        tvLanguages.text = stringBuilder.toString()
        stringBuilder.clear()

        country.currencies.forEach {
            stringBuilder.append(it.name).append("(${it.symbol})")
            if (it != country.currencies.last()) {
                stringBuilder.append(", ")
            }
        }
        tvCurrency.text = stringBuilder.toString()

        GlideToVectorYou.justLoadImage(context as MainActivity, Uri.parse(country.flagUrl), ivFlag)
    }
}
