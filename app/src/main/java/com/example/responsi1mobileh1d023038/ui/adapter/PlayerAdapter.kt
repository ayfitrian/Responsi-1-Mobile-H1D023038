package com.example.responsi1mobileh1d023038.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023038.data.model.Player
import com.example.responsi1mobileh1d023038.databinding.ItemPlayerBinding

class PlayerAdapter(
    private var players: List<Player>,
    private val onPlayerClicked: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        holder.binding.tvPlayerName.text = player.name ?: "Unknown Name"
        holder.binding.tvPlayerNationality.text = player.nationality ?: "Unknown Nationality"

        val cardColorString: String
        val nameTextColor: Int
        val nationalityTextColor: Int

        when (player.position) {
            "Goalkeeper" -> {
                cardColorString = "#B8860B"
                nameTextColor = Color.BLACK
                nationalityTextColor = Color.DKGRAY
            }

            "Centre-Back", "Right-Back", "Left-Back", "Defender", "Defence" -> {
                cardColorString = "#000080"
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }

            "Defensive Midfield", "Central Midfield", "Attacking Midfield",
            "Right Midfield", "Left Midfield", "Midfielder", "Midfield" -> {
                cardColorString = "#006400"
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }

            "Centre-Forward", "Second Striker", "Left Winger", "Right Winger",
            "Forward", "Attacker", "Striker", "Offence" -> {
                cardColorString = "#8B0000"
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }

            else -> {
                cardColorString = "#FFFFFF"
                nameTextColor = Color.BLACK
                nationalityTextColor = Color.DKGRAY
                if (player.position != null) {
                    android.util.Log.w("PlayerAdapter", "Posisi tidak dikenal: ${player.position} untuk ${player.name}")
                }
            }
        }

        holder.binding.cardPlayer.setCardBackgroundColor(Color.parseColor(cardColorString))
        holder.binding.tvPlayerName.setTextColor(nameTextColor)
        holder.binding.tvPlayerNationality.setTextColor(nationalityTextColor)

        holder.itemView.setOnClickListener {
            onPlayerClicked(player)
        }
    }

    fun updateData(newPlayers: List<Player>) {
        this.players = newPlayers
        notifyDataSetChanged()
    }
}