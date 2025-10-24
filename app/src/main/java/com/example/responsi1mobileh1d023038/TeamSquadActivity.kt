package com.example.responsi1mobileh1d023038

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023038.data.model.Player
import com.example.responsi1mobileh1d023038.databinding.ActivityTeamSquadBinding
import com.example.responsi1mobileh1d023038.ui.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023038.ui.fragment.PlayerDetailFragment
import com.example.responsi1mobileh1d023038.ui.viewmodel.TeamViewModel

class TeamSquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamSquadBinding
    private val viewModel: TeamViewModel by viewModels()
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamSquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Team Squad"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchData()
    }

    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter(emptyList()) { clickedPlayer ->
            showPlayerDetailFragment(clickedPlayer)
        }

        binding.recyclerViewSquad.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(this@TeamSquadActivity)
        }
    }
    private fun observeViewModel() {
        viewModel.teamData.observe(this) { teamResponse ->
            val players = teamResponse?.squad ?: emptyList()
            playerAdapter.updateData(players)
        }
    }

    private fun showPlayerDetailFragment(player: Player) {
        val fragment = PlayerDetailFragment(player)
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}