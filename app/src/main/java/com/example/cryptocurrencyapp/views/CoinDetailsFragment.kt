package com.example.cryptocurrencyapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentCoinDetailsBinding
import com.example.cryptocurrencyapp.viewmodels.CoinDetailsViewModel

class CoinDetailsFragment : Fragment() {

    private lateinit var viewModel: CoinDetailsViewModel
    private var _binding : FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[(CoinDetailsViewModel::class.java)]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}