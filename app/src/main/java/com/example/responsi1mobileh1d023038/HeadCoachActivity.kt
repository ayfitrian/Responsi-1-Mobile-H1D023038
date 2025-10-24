package com.example.responsi1mobileh1d023038

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023038.databinding.ActivityHeadCoachBinding
import com.example.responsi1mobileh1d023038.ui.viewmodel.TeamViewModel

class HeadCoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeadCoachBinding
    private val viewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Head Coach"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        observeViewModel()

        viewModel.fetchData()
    }
    private fun observeViewModel() {
        viewModel.teamData.observe(this) { teamResponse ->
            if (teamResponse != null) {
                teamResponse.coach?.let { coach ->
                    binding.tvCoachName.text = coach.name ?: "N/A"
                    binding.tvCoachDob.text = coach.dateOfBirth ?: "N/A"
                    binding.tvCoachNationality.text = coach.nationality ?: "N/A"
                }
            } else {
                binding.tvCoachName.text = "Failed to load data"
                binding.tvCoachDob.visibility = View.GONE
                binding.tvCoachNationality.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}