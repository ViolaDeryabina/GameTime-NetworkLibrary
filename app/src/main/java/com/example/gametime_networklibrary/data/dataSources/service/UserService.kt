package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST("login")
    suspend fun authorization(@Body authRequest: AuthRequest): RegisterResponse
    @Headers("accept: application/json")
    @POST("register")
    suspend fun registration(@Body registerRequest: RegisterRequest): RegisterResponse

    @POST("logout")
    suspend fun logout(@Header("Authorization") token: String): Logout

}