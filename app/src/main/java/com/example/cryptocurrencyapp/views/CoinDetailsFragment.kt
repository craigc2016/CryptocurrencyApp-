package com.example.cryptocurrencyapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.databinding.FragmentCoinDetailsBinding
import com.example.cryptocurrencyapp.models.CoinListDto
import com.example.cryptocurrencyapp.viewmodels.CoinDetailsViewModel
import com.plcoding.cryptocurrencyappyt.common.Constants

class CoinDetailsFragment : Fragment() {

    private lateinit var viewModel: CoinDetailsViewModel
    private var _binding : FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!
    private var mainActivityBinding : ActivityMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        mainActivityBinding = (requireActivity() as MainActivity).binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[(CoinDetailsViewModel::class.java)]
        val coinModel:CoinListDto? = arguments?.getParcelable(Constants.PARAM_COIN_ID)
        mainActivityBinding?.let {
            it.coinTitle.visibility(true)
            it.coinStatus.visibility(true)
            it.coinTitle.text = coinModel?.name
            it.coinStatus.text = if (coinModel?.isActive == true ) {
                context?.getString(R.string.coin_status_active_text)
            } else {
                context?.getString(R.string.coin_status_inactive_text)
            }
        }

        if (coinModel != null) {
            viewModel.getCoinDetail(coinModel.id).observe(viewLifecycleOwner){ response ->

                when(response){
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}