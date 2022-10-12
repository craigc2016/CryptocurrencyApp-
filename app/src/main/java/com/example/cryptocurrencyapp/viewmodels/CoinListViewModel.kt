package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.api.CoinInteractorImpl
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.models.CoinListDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinListViewModel : ViewModel() {

    private val coinInteractor = CoinInteractorImpl()

    fun getCoins(tag : String? = null) : LiveData<Resource<List<CoinListDto>>> {
        val coinListLiveData = MutableLiveData<Resource<List<CoinListDto>>>()
        if (coinListLiveData.value?.data.isNullOrEmpty()) coinListLiveData.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = if (tag != null) {
                    coinInteractor.getCoins().filter { it.id.contains(tag, ignoreCase = true) }.sortedBy { it.rank == 1 }
                } else {
                    coinInteractor.getCoins().sortedBy { it.rank == 1 }
                }
                coinListLiveData.postValue(Resource.Success(response))
            } catch (e : Exception) {
                coinListLiveData.postValue(Resource.Error("Error loading coin list"))
            }
        }
        return coinListLiveData
    }
}