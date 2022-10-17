package com.example.cryptocurrencyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ListItemBinding
import com.example.cryptocurrencyapp.models.TeamMember

class TeamsAdapter(private val teamsList : List<TeamMember>) : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsAdapter.TeamViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamsAdapter.TeamViewHolder, position: Int) {
        val teamMember = teamsList[position]
        holder.bind(teamMember)
    }

    override fun getItemCount(): Int = teamsList.size

    inner class TeamViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (teamMember: TeamMember) {
            binding.optionOneTitle.text = itemView.context.getString(R.string.team_name_title)
            binding.optionTwoTitle.text = itemView.context.getString(R.string.team_position_title)
            binding.optionOneText.text = teamMember.name
            binding.optionTwoText.text = teamMember.position
            binding.root.isEnabled = false
        }
    }
}