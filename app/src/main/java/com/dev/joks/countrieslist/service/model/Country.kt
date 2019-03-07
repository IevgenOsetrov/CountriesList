package com.dev.joks.countrieslist.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    @SerializedName("name")
    val name: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subRegion: String,
    @SerializedName("population")
    val population: Long,
    @SerializedName("area")
    val area: Double,
    @SerializedName("nativeName")
    val nativeName: String,
    @SerializedName("latlng")
    val location: List<Double>,
    @SerializedName("languages")
    val languages: List<Language>,
    @SerializedName("currencies")
    val currencies: List<Currency>,
    @SerializedName("flag")
    val flagUrl: String

) : Parcelable