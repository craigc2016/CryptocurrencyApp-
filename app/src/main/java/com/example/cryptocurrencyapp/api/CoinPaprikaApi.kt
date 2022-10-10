package com.example.cryptocurrencyapp.api

import com.example.cryptocurrencyapp.models.remote.CoinDetailDto
import com.example.cryptocurrencyapp.models.remote.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId")coinId: String): CoinDetailDto
}