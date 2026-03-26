package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dto.game.CategoryList
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.game.GameList
import com.example.gametime_networklibrary.data.dto.game.GameRecord
import com.example.gametime_networklibrary.domain.repository.GameRepository

class GameRepositoryImpl(private val api: GameService) : GameRepository {
    override suspend fun getListCategory(token: String): NetworkResult<CategoryList> {
        return ApiCallAdapter.safeApiCall { api.getListCategory("Bearer $token") }
    }

    override suspend fun getListGame(token: String): NetworkResult<GameList> {
        return ApiCallAdapter.safeApiCall { api.getListGame("Bearer $token") }
    }

    override suspend fun createGame(
        token: String,
        gameRequest: CreateGameRequest
    ): NetworkResult<GameRecord> {
        return ApiCallAdapter.safeApiCall { api.createGame("Bearer $token", gameRequest) }
    }

    override suspend fun getInformationGameByiId(
        token: String,
        id: String
    ): NetworkResult<GameRecord> {
        return ApiCallAdapter.safeApiCall { api.getInformationGameByiId("Bearer $token", id) }
    }
}