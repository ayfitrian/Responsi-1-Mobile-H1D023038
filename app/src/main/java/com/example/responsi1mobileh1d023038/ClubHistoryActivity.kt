package com.example.responsi1mobileh1d023038

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Import ViewBinding untuk layout ini
import com.example.responsi1mobileh1d023038.databinding.ActivityClubHistoryBinding

class ClubHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClubHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}