package com.example.responsi1mobileh1d023038

import android.content.Intent // Untuk pindah halaman
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023038.databinding.ActivityMainBinding // Import ViewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMenuContent()
        setupMenuClicks()
    }

    private fun setupMenuContent() {
        binding.menuHistory.imgIcon.setImageResource(R.drawable.ikon_bola)
        binding.menuHistory.tvLayout.text = "Club History"

        binding.menuCoach.imgIcon.setImageResource(R.drawable.ikon_pelatih)
        binding.menuCoach.tvLayout.text = "Head Coach"

        binding.menuSquad.imgIcon.setImageResource(R.drawable.ikon_team)
        binding.menuSquad.tvLayout.text = "Team Squad"
    }

    private fun setupMenuClicks() {
        binding.menuHistory.root.setOnClickListener {
            val intent = Intent(this, ClubHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.menuCoach.root.setOnClickListener {
            val intent = Intent(this, HeadCoachActivity::class.java)
            startActivity(intent)
        }

        binding.menuSquad.root.setOnClickListener {
            val intent = Intent(this, TeamSquadActivity::class.java)
            startActivity(intent)
        }
    }
}