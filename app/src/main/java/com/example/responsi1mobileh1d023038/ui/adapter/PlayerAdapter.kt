package com.example.responsi1mobileh1d023038.ui.adapter // Sesuaikan package name

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023038.data.model.Player // Import model Player
import com.example.responsi1mobileh1d023038.databinding.ItemPlayerBinding // Import ViewBinding

// Adapter ini menerima:
// 1. List awal pemain (bisa kosong)
// 2. Fungsi 'lambda' yang akan dipanggil saat item diklik, membawa data Player yang diklik
class PlayerAdapter(
    private var players: List<Player>,
    private val onPlayerClicked: (Player) -> Unit // Listener untuk klik (P4)
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    // ViewHolder: Memegang referensi ke view di item_player.xml
    inner class PlayerViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    // Membuat ViewHolder baru saat dibutuhkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        // Inflate (buat) layout item_player.xml menggunakan ViewBinding
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    // Mendapatkan jumlah total pemain
    override fun getItemCount() = players.size

    // Menghubungkan data pemain ke ViewHolder (tampilan) di posisi tertentu
    // Menghubungkan data pemain ke ViewHolder (tampilan) di posisi tertentu
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position] // Ambil data pemain di posisi ini

        // Masukkan data ke TextViews
        holder.binding.tvPlayerName.text = player.name ?: "Unknown Name"
        holder.binding.tvPlayerNationality.text = player.nationality ?: "Unknown Nationality"

        // === LOGIKA PEWARNAAN BARU (Sesuai Target Screenshot Baru) ===
        val cardColorString: String
        val nameTextColor: Int
        val nationalityTextColor: Int

        when (player.position) {
            "Goalkeeper" -> {
                cardColorString = "#B8860B" // Kuning tua (DarkGoldenrod)
                nameTextColor = Color.BLACK
                nationalityTextColor = Color.DKGRAY
            }
            "Defence"    -> {
                cardColorString = "#000080" // Biru tua (Navy)
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }
            "Midfield"   -> {
                cardColorString = "#006400" // Hijau tua (DarkGreen)
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }
            "Offence"    -> {
                cardColorString = "#8B0000" // Merah tua (DarkRed)
                nameTextColor = Color.WHITE
                nationalityTextColor = Color.LTGRAY
            }
            else         -> { // Posisi null atau tidak dikenal
                cardColorString = "#FFFFFF" // Putih (Default)
                nameTextColor = Color.BLACK
                nationalityTextColor = Color.DKGRAY
            }
        }

        holder.binding.cardPlayer.setCardBackgroundColor(Color.parseColor(cardColorString))
        holder.binding.tvPlayerName.setTextColor(nameTextColor)
        holder.binding.tvPlayerNationality.setTextColor(nationalityTextColor)
        // =======================================================

        // Atur listener klik pada seluruh item view (CardView)
        holder.itemView.setOnClickListener {
            onPlayerClicked(player) // Panggil lambda 'onPlayerClicked' dan kirim data 'player'
        }
    }

    // Fungsi untuk mengupdate data pemain di adapter (P3)
    fun updateData(newPlayers: List<Player>) {
        this.players = newPlayers
        notifyDataSetChanged() // Beritahu RecyclerView untuk refresh tampilan
    }
}