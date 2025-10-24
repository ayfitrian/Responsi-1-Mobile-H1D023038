package com.example.responsi1mobileh1d023038 // Pastikan package name sesuai

import android.os.Bundle
import android.view.View // Untuk mengatur visibility
import androidx.activity.viewModels // Untuk 'by viewModels()'
import androidx.appcompat.app.AppCompatActivity
// Import ViewBinding untuk layout ini
import com.example.responsi1mobileh1d023038.databinding.ActivityHeadCoachBinding
// Import ViewModel kita
import com.example.responsi1mobileh1d023038.ui.viewmodel.TeamViewModel

class HeadCoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeadCoachBinding
    // Panggil ViewModel menggunakan 'by viewModels()' (dari library activity-ktx)
    private val viewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur judul ActionBar dan tombol kembali
        supportActionBar?.title = "Head Coach"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mulai "mendengarkan" perubahan data dari ViewModel
        observeViewModel()

        // Minta ViewModel untuk mengambil data dari API
        viewModel.fetchData()
    }

    // Fungsi untuk mengamati LiveData dari ViewModel
    private fun observeViewModel() {
        // Mengamati teamData (yang berisi info pelatih)
        viewModel.teamData.observe(this) { teamResponse ->
            // 'teamResponse' bisa null jika API error
            if (teamResponse != null) {
                // Ambil data pelatih, 'let' memastikan tidak null
                teamResponse.coach?.let { coach ->
                    // Masukkan data ke TextViews
                    binding.tvCoachName.text = coach.name ?: "N/A" // Tampilkan N/A jika nama null
                    binding.tvCoachDob.text = coach.dateOfBirth ?: "N/A"
                    binding.tvCoachNationality.text = coach.nationality ?: "N/A"
                }
            } else {
                // Handle jika data null (misal: tampilkan pesan error)
                binding.tvCoachName.text = "Failed to load data"
                binding.tvCoachDob.visibility = View.GONE // Sembunyikan field lain
                binding.tvCoachNationality.visibility = View.GONE
            }
        }
    }

    // Fungsi agar tombol kembali di ActionBar berfungsi
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}