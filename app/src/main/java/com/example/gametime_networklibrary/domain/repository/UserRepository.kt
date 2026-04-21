package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse

interface UserRepository {
    suspend fun authorization(authRequest: AuthRequest): NetworkResult<RegisterResponse>
    suspend fun registration(registerRequest: RegisterRequest): NetworkResult<RegisterResponse>

    suspend fun logout(token: String):  NetworkResult<Logout>

}