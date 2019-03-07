package com.dev.joks.countrieslist.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Currency(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
) : Parcelable