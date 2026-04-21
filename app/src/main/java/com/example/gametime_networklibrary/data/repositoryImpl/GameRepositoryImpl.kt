package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.Game
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameList
import com.example.gametime_networklibrary.data.dto.gameTime.game.LoginGameResponse
import com.example.gametime_networklibrary.domain.repository.GameRepository

class GameRepositoryImpl(private val api: GameService) : GameRepository {
//    override suspend fun getListCategory(token: String): NetworkResult<CategoryList> {
//        return ApiCallAdapter.safeApiCall { api.getListCategory("Bearer $token") }
//    }

    override suspend fun getListGame(
        token: String,
        status: String,
        type: String
    ): NetworkResult<GameList> {
        return ApiCallAdapter.safeApiCall { api.getListGame("Bearer $token",status,type) }
    }

    override suspend fun createGame(
        token: String,
        gameRequest: CreateGameRequest
    ): NetworkResult<CreateGameResponse> {
        return ApiCallAdapter.safeApiCall { api.createGame("Bearer $token", gameRequest) }

    }

    override suspend fun getInformationGameByiId(
        token: String,
        id: Int
    ): NetworkResult<Game> {
        return ApiCallAdapter.safeApiCall { api.getInformationGameByiId("Bearer $token", id) }
    }

    override suspend fun loginGame(
        token: String,
        id: Int
    ): NetworkResult<LoginGameResponse> {
        return ApiCallAdapter.safeApiCall { api.loginGame("Bearer $token", id) }
    }

    override suspend fun safeResultGame(
        token: String,
        id: Int,
        finishGameRequest: FinishGameRequest
    ): NetworkResult<FinishGameResponse> {
        return ApiCallAdapter.safeApiCall { api.safeResultGame("Bearer $token", id,finishGameRequest) }
    }
}