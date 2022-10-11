package com.example.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.common.visibility
import com.example.cryptocurrencyapp.databinding.ListItemBinding
import com.example.cryptocurrencyapp.models.CoinListDto

class CoinListAdapter(
    private val coinList : List<CoinListDto>,
    private val onItemClickListener :((CoinListDto) -> Unit)?
): RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinItem = coinList[position]
        holder.bind(coinItem)
    }

    override fun getItemCount(): Int = coinList.size

    inner class CoinViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coinItem : CoinListDto) {
            //set Titles
            binding.optionOneTitle.text = itemView.context.getString(R.string.coin_name_title)
            binding.optionTwoText.text = itemView.context.getString(R.string.coin_code_tile)
            binding.optionThreeText.text =  itemView.context.getString(R.string.coin_symbol_title)

            //set item text
            binding.optionOneText.text = coinItem.name
            binding.optionTwoText.text = coinItem.id
            if (coinItem.isActive) {
                binding.optionThreeTitle.visibility(true)
                binding.optionThreeText.visibility(true)
                binding.optionThreeText.text = coinItem.symbol
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let { listener ->
                    listener(coinItem)
                }
            }
        }
    }

}