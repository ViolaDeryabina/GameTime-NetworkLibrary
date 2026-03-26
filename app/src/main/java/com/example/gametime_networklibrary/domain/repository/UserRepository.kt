package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthResponse
import retrofit2.http.Body

interface UserRepository {
    suspend fun authorization(authRequest: AuthRequest): NetworkResult<AuthResponse>
}