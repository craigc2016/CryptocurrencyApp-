package com.example.cryptocurrencyapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinListDto(
    val id : String,
    val name : String,
    val symbol : String,
    val rank : Int,
    @SerializedName("is_new")
    val isNew : Boolean,
    @SerializedName("is_active")
    val isActive : Boolean,
    val type : String
) : Parcelable

