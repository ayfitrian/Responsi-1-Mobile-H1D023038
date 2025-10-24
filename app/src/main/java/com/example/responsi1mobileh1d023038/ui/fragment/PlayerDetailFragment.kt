package com.example.responsi1mobileh1d023038.ui.fragment // Sesuaikan package

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment // Import penting!
import com.example.responsi1mobileh1d023038.data.model.Player // Import model
import com.example.responsi1mobileh1d023038.databinding.FragmentPlayerDetailBinding // Import ViewBinding

// Warisi dari BottomSheetDialogFragment (P4 - Tahap H.a)
class PlayerDetailFragment(
    private val player: Player // Terima data Player saat dibuat (P4 - Tahap H.b)
) : BottomSheetDialogFragment() {

    // Setup ViewBinding (P4 - Tahap H.c)
    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout fragment_player_detail.xml
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Panggil fungsi untuk menampilkan data
        displayPlayerData()
    }

    // Fungsi untuk memasukkan data Player ke TextViews (P4 - Tahap H.g)
    private fun displayPlayerData() {
        binding.tvDetailName.text = player.name ?: "N/A"
        binding.tvDetailDob.text = player.dateOfBirth ?: "N/A"
        binding.tvDetailNationality.text = player.nationality ?: "N/A"
        binding.tvDetailPosition.text = player.position ?: "N/A"
    }

    // Bersihkan binding saat Fragment dihancurkan (P4 - Tahap H.f)
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mencegah memory leak
    }
}