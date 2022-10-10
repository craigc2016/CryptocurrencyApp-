package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.api.CoinInteractorImpl
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.models.local.CoinDetail
import com.example.cryptocurrencyapp.models.remote.CoinDetailDto
import com.example.cryptocurrencyapp.models.remote.CoinDto
import com.example.cryptocurrencyapp.models.remote.toCoinDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinDetailsViewModel : ViewModel() {

    private val coinDetailLiveData = MutableLiveData<Resource<CoinDetail>>()
    private val coinInteractor = CoinInteractorImpl()

    fun getCoinDetail(coinId : String) : LiveData<Resource<CoinDetail>> {
        coinDetailLiveData.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = coinInteractor.getCoin(coinId).toCoinDetail()
                coinDetailLiveData.postValue(Resource.Success(response))
            } catch (e : Exception) {
                coinDetailLiveData.postValue(Resource.Error("Error has Occurred"))
            }
        }
        return coinDetailLiveData
    }
}