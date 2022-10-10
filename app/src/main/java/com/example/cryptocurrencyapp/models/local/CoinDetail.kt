package com.example.cryptocurrencyapp.models.local

import com.example.cryptocurrencyapp.models.remote.TeamMember


data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: TeamMember
)
