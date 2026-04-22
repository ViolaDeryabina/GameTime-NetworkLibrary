package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.gameTime.game.GameData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StatisticsService {
    @GET("statistics/played-games")
    suspend fun getStatisticsGame(@Header("Authorization")token: String): GameData
}