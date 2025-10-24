package com.example.responsi1mobileh1d023038.data.network

import com.example.responsi1mobileh1d023038.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FootballApi {
    @GET("v4/teams/512")
    suspend fun getTeamDetails(
        @Header("X-Auth-Token") token: String
    ): Response<TeamResponse>
}