package com.example.cryptocurrencyapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.adapter.CoinListAdapter
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.example.cryptocurrencyapp.viewmodels.CoinListViewModel

class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinListViewModel
    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private var coinListAdapter : CoinListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinListViewModel::class.java]

        coinListInit()
    }


    private fun coinListInit() {
        viewModel.getCoins().observe(viewLifecycleOwner){ response ->

            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { coinList ->
                        coinListAdapter = CoinListAdapter(coinList) { coin ->

                            val bundle = Bundle().apply {
                                putParcelable("coin_model",coin)
                            }
                            findNavController().navigate(
                                R.id.action_coinListScreen_to_coinDetailsFragment,
                                bundle
                            )
                        }
                    }
                    recyclerViewInit()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun recyclerViewInit() {
        binding.coinList.apply {
            adapter = coinListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() = binding.coinListProgressBar.visibility(true)

    private fun hideProgressBar() = binding.coinListProgressBar.visibility(false)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}