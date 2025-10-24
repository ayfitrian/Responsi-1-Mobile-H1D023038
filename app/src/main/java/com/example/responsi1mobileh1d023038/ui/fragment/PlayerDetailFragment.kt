package com.example.responsi1mobileh1d023038.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment // Import penting!
import com.example.responsi1mobileh1d023038.data.model.Player // Import model
import com.example.responsi1mobileh1d023038.databinding.FragmentPlayerDetailBinding // Import ViewBinding


class PlayerDetailFragment(
    private val player: Player
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayPlayerData()
    }

    private fun displayPlayerData() {
        binding.tvDetailName.text = player.name ?: "N/A"
        binding.tvDetailDob.text = player.dateOfBirth ?: "N/A"
        binding.tvDetailNationality.text = player.nationality ?: "N/A"
        binding.tvDetailPosition.text = player.position ?: "N/A"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}