package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.game.CategoryList
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.game.GameList
import com.example.gametime_networklibrary.data.dto.game.GameRecord
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Path

interface GameRepository {
    suspend fun getListCategory(token: String): NetworkResult<CategoryList>
    suspend fun getListGame(token: String): NetworkResult<GameList>
    suspend fun createGame(token: String, gameRequest: CreateGameRequest): NetworkResult<GameRecord>

    suspend fun getInformationGameByiId(token: String, id: String): NetworkResult<GameRecord>


}