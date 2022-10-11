package com.example.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.makePartTextBold
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.CoinListItemBinding
import com.example.cryptocurrencyapp.models.CoinListDto

class CoinListAdapter(private val coinList : List<CoinListDto>, private val onItemClickListener :((CoinListDto) -> Unit)?): RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinItem = coinList[position]
        holder.bind(coinItem)
    }

    override fun getItemCount(): Int = coinList.size

    inner class CoinViewHolder(private val binding : CoinListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coinItem : CoinListDto) {
            binding.coinName.text = coinItem.name
            binding.coinCode.text = coinItem.id
            if (coinItem.isActive) {
                binding.coinSymbolTitle.visibility(true)
                binding.coinSymbol.visibility(true)
                binding.coinSymbol.text = coinItem.symbol
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let { listener ->
                    listener(coinItem)
                }
            }
        }
    }

}