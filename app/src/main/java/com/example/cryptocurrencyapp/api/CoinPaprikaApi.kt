package com.example.cryptocurrencyapp.api

import com.example.cryptocurrencyapp.models.CoinListDto
import com.example.cryptocurrencyapp.models.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinListDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId")coinId: String): CoinDetailDto
}