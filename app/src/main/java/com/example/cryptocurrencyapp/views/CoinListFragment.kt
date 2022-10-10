package com.example.cryptocurrencyapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.adapter.CoinListAdapter
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.example.cryptocurrencyapp.viewmodels.CoinListViewModel

class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinListViewModel
    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    private lateinit var coinListAdapter : CoinListAdapter

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
        val navController = Navigation.findNavController(view)

        coinListInit()
//        recyclerViewInit()
    }

    private fun recyclerViewInit() {
        binding.coinList.apply {
            adapter = coinListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun coinListInit() {
        viewModel.getCoins().observe(viewLifecycleOwner){ response ->

            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { coinList ->
                        coinListAdapter = CoinListAdapter(coinList)
                        binding.coinList.apply {
                            adapter = coinListAdapter
                            layoutManager = LinearLayoutManager(activity)
                        }

//                        val totalResults = viewModel.totalResults()
//                        coinAdapter?.differ?.submitList(coinList)
//                        pages = if (totalResults % 20 == 0) {
//                            totalResults / 20
//                        }else{
//                            totalResults / 20 + 1
//                        }
//                        isLastPage = page == pages
                    }
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

    private val onScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.coinList.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate){
                page ++
                viewModel.getCoins()
                isScrolling = false
            }
        }
    }

    private fun showProgressBar() = binding.coinListProgressBar.visibility(true)

    private fun hideProgressBar() = binding.coinListProgressBar.visibility(false)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}