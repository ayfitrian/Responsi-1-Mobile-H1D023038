package com.example.responsi1mobileh1d023038.ui.viewmodel

import android.util.Log // Untuk debugging (melihat pesan di Logcat)
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023038.data.model.TeamResponse
import com.example.responsi1mobileh1d023038.data.network.Constants
import com.example.responsi1mobileh1d023038.data.network.RetrofitInstance
import kotlinx.coroutines.launch // Untuk menjalankan tugas di background

// Warisi dari ViewModel bawaan Android [cite: 2228]
class TeamViewModel : ViewModel() {

    // MutableLiveData: Bisa diubah isinya (hanya oleh ViewModel) [cite: 2231]
    private val _teamData = MutableLiveData<TeamResponse?>() // Tambah ? agar bisa null
    // LiveData: Hanya bisa dibaca (oleh Activity/Fragment) [cite: 2233]
    val teamData: LiveData<TeamResponse?> = _teamData

    // Fungsi untuk mengambil data dari API
    fun fetchData() {
        Log.d("TeamViewModel", "fetchData() dipanggil") // Pesan debug
        // viewModelScope.launch: Jalankan di background agar UI tidak macet [cite: 2239]
        viewModelScope.launch {
            try {
                // Panggil Retrofit untuk mengambil data
                val response = RetrofitInstance.api.getTeamDetails(Constants.API_TOKEN)
                if (response.isSuccessful) {
                    // Jika sukses (kode 200-299), update LiveData [cite: 2248]
                    _teamData.postValue(response.body())
                    Log.d("TeamViewModel", "Data sukses diambil: ${response.body()}")
                } else {
                    // Jika gagal, tampilkan pesan error di Logcat [cite: 2251]
                    Log.e("TeamViewModel", "Error: ${response.code()} - ${response.message()}")
                    _teamData.postValue(null) // Kirim null jika error
                }
            } catch (e: Exception) {
                // Jika ada error jaringan atau lainnya [cite: 2253]
                Log.e("TeamViewModel", "Exception: ${e.message}", e)
                _teamData.postValue(null) // Kirim null jika exception
            }
        }
    }
}