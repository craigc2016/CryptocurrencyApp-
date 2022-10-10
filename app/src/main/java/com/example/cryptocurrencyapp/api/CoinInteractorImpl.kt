package com.example.cryptocurrencyapp.api

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.models.remote.CoinDetailDto
import com.example.cryptocurrencyapp.models.remote.CoinDto

interface CoinInteractor {
    suspend fun getCoins() : List<CoinDto>
    suspend fun getCoin(coinId : String) : CoinDetailDto
}

class CoinInteractorImpl : CoinInteractor {

    private val api = RetrofitUtils.INSTANCE.providesPaprikaApi()

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}