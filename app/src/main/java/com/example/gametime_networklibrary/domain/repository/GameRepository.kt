package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.Game
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameList
import com.example.gametime_networklibrary.data.dto.gameTime.game.LoginGameResponse

interface GameRepository {
    //suspend fun getListCategory(token: String): NetworkResult<CategoryList>
    suspend fun getListGame(token: String, status: String,type: String,):  NetworkResult<GameList>
    suspend fun createGame(token: String, gameRequest: CreateGameRequest):  NetworkResult<CreateGameResponse>

    suspend fun getInformationGameByiId(token: String, id: Int): NetworkResult<Game>

    suspend fun loginGame(token: String,  id: Int):  NetworkResult<LoginGameResponse>
    suspend fun safeResultGame(token: String,  id: Int,finishGameRequest: FinishGameRequest):   NetworkResult<FinishGameResponse>




}