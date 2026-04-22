package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameData

interface StatisticsRepository {
    suspend fun getStatisticsGame(token: String):  NetworkResult<GameData>
}