package com.example.responsi1mobileh1d023038

import android.content.Intent // Untuk pindah halaman
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023038.databinding.ActivityMainBinding // Import ViewBinding

class MainActivity : AppCompatActivity() {

    // Deklarasi ViewBinding (P2 - Tahap 5a)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi ViewBinding (P2 - Tahap 5c)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Tampilkan layout utama

        // Isi teks dan ikon menu
        setupMenuContent()
        // Atur agar menu bisa diklik
        setupMenuClicks()
    }

    // Fungsi untuk mengisi konten menu (P2 - Tahap 5d)
    private fun setupMenuContent() {
        // Mengakses komponen di dalam <include>
        binding.menuHistory.imgIcon.setImageResource(R.drawable.ikon_bola)
        binding.menuHistory.tvLayout.text = "Club History"

        binding.menuCoach.imgIcon.setImageResource(R.drawable.ikon_pelatih)
        binding.menuCoach.tvLayout.text = "Head Coach"

        binding.menuSquad.imgIcon.setImageResource(R.drawable.ikon_team)
        binding.menuSquad.tvLayout.text = "Team Squad"
    }

    // Fungsi untuk mengatur klik menu (P2 - Tahap 6c)
    private fun setupMenuClicks() {
        // Pindah ke ClubHistoryActivity saat menu history diklik
        binding.menuHistory.root.setOnClickListener {
            val intent = Intent(this, ClubHistoryActivity::class.java)
            startActivity(intent) // Jalankan perpindahan
        }

        // Pindah ke HeadCoachActivity saat menu coach diklik
        binding.menuCoach.root.setOnClickListener {
            val intent = Intent(this, HeadCoachActivity::class.java)
            startActivity(intent)
        }

        // Pindah ke TeamSquadActivity saat menu squad diklik
        binding.menuSquad.root.setOnClickListener {
            val intent = Intent(this, TeamSquadActivity::class.java)
            startActivity(intent)
        }
    }
}