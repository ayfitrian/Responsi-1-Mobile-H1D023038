package com.example.responsi1mobileh1d023038.data.model

import com.google.gson.annotations.SerializedName

// Ini 'cetakan' utama untuk seluruh data tim dari API
data class TeamResponse(
    @SerializedName("coach") // Nama field di JSON harus sama persis
    val coach: Coach?, // Data pelatih (bisa null)

    @SerializedName("squad")
    val squad: List<Player>? // Daftar pemain (bisa null)
)

// Cetakan untuk data Pelatih
data class Coach(
    @SerializedName("name")
    val name: String?,

    @SerializedName("nationality")
    val nationality: String?,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String? // Tanggal lahir
)

// Cetakan untuk data Pemain
data class Player(
    @SerializedName("name")
    val name: String?,

    @SerializedName("position")
    val position: String?, // Posisi (Goalkeeper, Defender, dll)

    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,

    @SerializedName("nationality")
    val nationality: String?
)