package com.example.responsi1mobileh1d023038 // Sesuaikan package

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023038.data.model.Player // Import model
import com.example.responsi1mobileh1d023038.databinding.ActivityTeamSquadBinding // Import ViewBinding
import com.example.responsi1mobileh1d023038.ui.adapter.PlayerAdapter // Import Adapter
import com.example.responsi1mobileh1d023038.ui.fragment.PlayerDetailFragment // Import Fragment
import com.example.responsi1mobileh1d023038.ui.viewmodel.TeamViewModel // Import ViewModel

class TeamSquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamSquadBinding
    private val viewModel: TeamViewModel by viewModels() // Panggil ViewModel
    private lateinit var playerAdapter: PlayerAdapter // Deklarasi Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Team Squad"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Siapkan RecyclerView
        setupRecyclerView()
        // Amati data dari ViewModel
        observeViewModel()
        // Minta data ke ViewModel
        viewModel.fetchData()
    }

    private fun setupRecyclerView() {
        // Buat instance Adapter, berikan list kosong awal
        // dan berikan aksi (lambda) saat item diklik (P4 - Tahap I.f)
        playerAdapter = PlayerAdapter(emptyList()) { clickedPlayer ->
            // Panggil fungsi untuk menampilkan detail pemain
            showPlayerDetailFragment(clickedPlayer)
        }

        // Pasang Adapter dan LayoutManager ke RecyclerView (P3)
        binding.recyclerViewSquad.apply {
            adapter = playerAdapter
            // Tampilkan item secara vertikal
            layoutManager = LinearLayoutManager(this@TeamSquadActivity)
        }
    }

    private fun observeViewModel() {
        // Amati teamData dari ViewModel
        viewModel.teamData.observe(this) { teamResponse ->
            // Ambil daftar pemain (squad) dari data, jika null beri list kosong
            val players = teamResponse?.squad ?: emptyList()
            // Update data di Adapter
            playerAdapter.updateData(players)
        }
    }

    // Fungsi untuk menampilkan BottomSheetFragment (P4)
    private fun showPlayerDetailFragment(player: Player) {
        // Buat instance PlayerDetailFragment dan kirim data 'player'
        val fragment = PlayerDetailFragment(player)
        // Tampilkan fragment menggunakan FragmentManager
        fragment.show(supportFragmentManager, fragment.tag) // 'tag' bisa null atau string unik
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() // Kembali ke halaman sebelumnya
        return true
    }
}