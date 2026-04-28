package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.Logout
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterRequest
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.RegisterResponse
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.AuthRequest
import com.example.gametime_networklibrary.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserService) : UserRepository {

    override suspend fun authorization(authRequest: AuthRequest): NetworkResult<RegisterResponse> {
        return ApiCallAdapter.safeApiCall { api.authorization(authRequest) }
    }

    override suspend fun registration(registerRequest: RegisterRequest): NetworkResult<RegisterResponse> {
        return ApiCallAdapter.safeApiCall { api.registration(registerRequest) }
    }

    override suspend fun logout(token: String): NetworkResult<Logout> {
        return ApiCallAdapter.safeApiCall { api.logout("Bearer $token") }
    }
}