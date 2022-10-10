package com.example.cryptocurrencyapp.api

import com.example.cryptocurrencyapp.models.CoinListDto
import com.example.cryptocurrencyapp.models.CoinDetailDto

interface CoinInteractor {
    suspend fun getCoins() : List<CoinListDto>
    suspend fun getCoin(coinId : String) : CoinDetailDto
}

class CoinInteractorImpl : CoinInteractor {

    private val api = RetrofitUtils.INSTANCE.providesPaprikaApi()

    override suspend fun getCoins(): List<CoinListDto> {
        return api.getCoins()
    }

    override suspend fun getCoin(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}