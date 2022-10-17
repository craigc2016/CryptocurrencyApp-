package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.api.CoinInteractorImpl
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.models.CoinDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinDetailsViewModel : ViewModel() {

    private val coinDetailLiveData = MutableLiveData<Resource<CoinDetailDto>>()
    private val coinInteractor = CoinInteractorImpl()

    fun getCoinDetail(coinId : String) : LiveData<Resource<CoinDetailDto>> {
        coinDetailLiveData.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = coinInteractor.getCoin(coinId)
                coinDetailLiveData.postValue(Resource.Success(response))
            } catch (e : Exception) {
                coinDetailLiveData.postValue(Resource.Error("Error loading Coin Details"))
            }
        }
        return coinDetailLiveData
    }
}