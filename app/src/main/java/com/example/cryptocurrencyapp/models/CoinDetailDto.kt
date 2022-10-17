package com.example.cryptocurrencyapp.models

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val description : String?,
    val name: String,
    val rank: Int,
    val symbol: String,
    val logo: String,
    val type: String,
    val tags: List<Tag>?,
    val team: List<TeamMember>?
)


