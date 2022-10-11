package com.example.cryptocurrencyapp.views

import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.adapter.TagsAdapter
import com.example.cryptocurrencyapp.adapter.TeamsAdapter
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.databinding.FragmentCoinDetailsBinding
import com.example.cryptocurrencyapp.models.CoinDetailDto
import com.example.cryptocurrencyapp.models.CoinListDto
import com.example.cryptocurrencyapp.viewmodels.CoinDetailsViewModel
import com.plcoding.cryptocurrencyappyt.common.Constants

class CoinDetailsFragment : Fragment() {

    private lateinit var viewModel: CoinDetailsViewModel
    private var _binding : FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!
    private var mainActivityBinding : ActivityMainBinding? = null
    private var teamsAdapter : TeamsAdapter? = null
    private var tagsAdapter : TagsAdapter? = null

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

        setUpCoinDetail(coinModel)
        handleBackPressed()
    }

    private fun setUpCoinDetail(coinModel: CoinListDto?) {
        if (coinModel != null) {
            setToolBarText(coinModel)

            viewModel.getCoinDetail(coinModel.id).observe(viewLifecycleOwner){ response ->

                when(response){
                    is Resource.Success -> {
                        hideProgressBar()
                        if (response.data != null) {
                            validateResponse(response.data)
                            recyclerViewsInit()
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        Toast.makeText(context,response.message, Toast.LENGTH_LONG).show()
                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            }
        }
    }

    private fun validateResponse(response : CoinDetailDto) {
        binding.coinDescription.text = response.description ?: response.name

        if (response.tags == null || response.tags.isEmpty()) {
            binding.tagsTitle.visibility(false)
            binding.tagsList.visibility(false)
        } else {
            tagsAdapter = TagsAdapter(response.tags) { tag ->
                val bundle = Bundle().apply {
                    putParcelable(Constants.PARAM_TAG_ID,tag)
                }
                resetToolBar()
                findNavController().navigate(
                    R.id.action_coinDetailsFragment_to_coinListScreen,
                    bundle
                )
            }
        }
        if (response.team == null || response.team.isEmpty()) {
            binding.teamsTitle.visibility(false)
            binding.teamsList.visibility(false)
        } else {
            teamsAdapter = TeamsAdapter(response.team)
        }

        // Load logo to tool bar need url from response
        mainActivityBinding?.let {
            Glide.with(requireContext())
                .load(response.logo)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        it.coinLogo.visibility(false)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable?>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .apply(RequestOptions().override(180, 180))
                .into(it.coinLogo)
        }
    }

    private fun setToolBarText(coinModel : CoinListDto?) {
        mainActivityBinding?.let {
            it.coinStatus.visibility(true)
            it.coinTitle.visibility(true)
            it.coinLogo.visibility(true)
            it.coinTitle.text = coinModel?.name
            it.coinStatus.text = if (coinModel?.isActive == true ) {
                context?.getString(R.string.coin_status_active_text)
            } else {
                context?.getString(R.string.coin_status_inactive_text)
            }
        }
    }

    private fun recyclerViewsInit() {
        binding.teamsList.apply {
            adapter = teamsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        binding.tagsList.apply {
            adapter = tagsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun handleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                resetToolBar()
                popBack()
            }
        })
    }

    private fun resetToolBar() {
        mainActivityBinding?.let {
            it.coinLogo.setImageDrawable(null)
            it.coinTitle.text = ""
            it.coinStatus.text = ""
        }
    }

    private fun popBack() {
        try {
            val view = view
            if (view != null) {
                val navController = findNavController(view)
                navController.popBackStack()
            }
        } catch (e: Exception) {
        }
    }

    private fun showProgressBar() = binding.coinDetailsProgressBar.visibility(true)

    private fun hideProgressBar() = binding.coinDetailsProgressBar.visibility(false)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}