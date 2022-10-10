package com.example.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.databinding.CoinListItemBinding
import com.example.cryptocurrencyapp.models.CoinListDto

class CoinListAdapter(private val coinList : List<CoinListDto>): RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

//    private val callback = object : DiffUtil.ItemCallback<CoinListDto>(){
//        override fun areItemsTheSame(oldItem: CoinListDto, newItem: CoinListDto): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(oldItem: CoinListDto, newItem: CoinListDto): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinItem = coinList[position]//differ.currentList[position]
        holder.bind(coinItem)
    }

    override fun getItemCount(): Int = coinList.size//differ.currentList.size

    inner class CoinViewHolder(private val binding : CoinListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coinItem : CoinListDto) {
            binding.coinName.text = coinItem.name
        }
    }

    private var onItemClickListener :((CoinListDto) -> Unit) ? = null

    fun setOnItemClickListener(listener: (CoinListDto) -> Unit) {
        onItemClickListener = listener
    }

}