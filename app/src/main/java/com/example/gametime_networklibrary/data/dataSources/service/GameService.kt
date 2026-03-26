package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.game.CategoryList
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.dto.game.GameList
import com.example.gametime_networklibrary.data.dto.game.GameRecord
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {
    @GET("collections/category/records")
    suspend fun getListCategory(@Header("Authorization")token: String): CategoryList

    @GET("collections/game/records")
    suspend fun getListGame(@Header("Authorization")token: String): GameList

    @POST("collections/games/records")
    suspend fun createGame(@Header("Authorization")token: String, @Body gameRequest: CreateGameRequest): GameRecord

    @GET("collections/games/records/{id}?expand=category,host")
    suspend fun getInformationGameByiId(@Header("Authorization")token: String, @Path("id")id: String): GameRecord
}