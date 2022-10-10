package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.api.CoinInteractorImpl
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.models.remote.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinListViewModel : ViewModel() {

    private val coinListLiveData = MutableLiveData<Resource<List<CoinDto>>>()
    private val coinInteractor = CoinInteractorImpl()

    fun getCoins() : LiveData<Resource<List<CoinDto>>> {
        coinListLiveData.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = coinInteractor.getCoins()
                coinListLiveData.postValue(Resource.Success(response))
            } catch (e : Exception) {
                coinListLiveData.postValue(Resource.Error("Error has Occurred"))
            }
        }
        return coinListLiveData
    }
}