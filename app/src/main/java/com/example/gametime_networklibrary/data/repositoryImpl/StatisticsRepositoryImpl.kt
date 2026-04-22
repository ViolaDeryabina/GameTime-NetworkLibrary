package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.StatisticsService
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameData
import com.example.gametime_networklibrary.domain.repository.StatisticsRepository

class StatisticsRepositoryImpl(private val api: StatisticsService) : StatisticsRepository {
    override suspend fun getStatisticsGame(token: String): NetworkResult<GameData> {
        return ApiCallAdapter.safeApiCall { api.getStatisticsGame("Bearer ${token}") }
    }
}
