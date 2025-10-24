package com.example.responsi1mobileh1d023038.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023038.data.model.TeamResponse
import com.example.responsi1mobileh1d023038.data.network.Constants
import com.example.responsi1mobileh1d023038.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {


    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData

    fun fetchData() {
        Log.d("TeamViewModel", "fetchData() dipanggil")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTeamDetails(Constants.API_TOKEN)
                if (response.isSuccessful) {
                    _teamData.postValue(response.body())
                    Log.d("TeamViewModel", "Data sukses diambil: ${response.body()}")
                } else {
                    Log.e("TeamViewModel", "Error: ${response.code()} - ${response.message()}")
                    _teamData.postValue(null)
                }
            } catch (e: Exception) {
                Log.e("TeamViewModel", "Exception: ${e.message}", e)
                _teamData.postValue(null)
            }
        }
    }
}