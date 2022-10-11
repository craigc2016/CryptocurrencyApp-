package com.example.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ListItemBinding
import com.example.cryptocurrencyapp.models.CoinListDto
import com.example.cryptocurrencyapp.models.Tag

class TagsAdapter(
    private val tagsList : List<Tag>,
    private val onItemClickListener :((Tag) -> Unit)?
) : RecyclerView.Adapter<TagsAdapter.TagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.TagViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsAdapter.TagViewHolder, position: Int) {
        val tag = tagsList[position]
        holder.bind(tag)
    }

    override fun getItemCount(): Int = tagsList.size

    inner class TagViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (tag: Tag) {
            binding.optionOneTitle.text = itemView.context.getString(R.string.tag_name_title)
            binding.optionTwoTitle.text = itemView.context.getString(R.string.tag_counter_title)
            binding.optionOneText.text = tag.name
            binding.optionTwoText.text = tag.coin_counter.toString()

            binding.root.setOnClickListener {
                onItemClickListener?.let { listener ->
                    listener(tag)
                }
            }
        }
    }
}