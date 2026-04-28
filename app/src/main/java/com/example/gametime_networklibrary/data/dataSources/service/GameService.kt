package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreateGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameRequest
import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameResponse
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameDto
import com.example.gametime_networklibrary.data.dto.gameTime.game.GameListDto
import com.example.gametime_networklibrary.data.dto.gameTime.game.LoginGameResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {
    @GET("games")
    suspend fun getListGame(@Header("Authorization")token: String, @Query("status")status: String,@Query("type")type: String,): GameListDto

    @POST("games")
    suspend fun createGame(@Header("Authorization")token: String, @Body gameRequest: CreateGameRequest): CreateGameResponse

    @GET("games/{id}")
    suspend fun getInformationGameByiId(@Header("Authorization")token: String, @Path("id")id: Int): GameDto

    @POST("games/{id}/join")
    suspend fun loginGame(@Header("Authorization")token: String,  @Path("id")id: Int): LoginGameResponse

    @POST("games/{id}/finish")
    suspend fun safeResultGame(@Header("Authorization")token: String,  @Path("id")id: Int,@Body finishGameRequest: FinishGameRequest): FinishGameResponse


}