package com.example.cryptocurrencyapp.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FragmentCoinListBinding
import com.example.cryptocurrencyapp.viewmodels.CoinListViewModel

class CoinListFragment : Fragment() {

    private lateinit var viewModel: CoinListViewModel
    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!

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
        binding.testButton.setOnClickListener {
            navController.navigate(R.id.action_coinListScreen_to_coinDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}