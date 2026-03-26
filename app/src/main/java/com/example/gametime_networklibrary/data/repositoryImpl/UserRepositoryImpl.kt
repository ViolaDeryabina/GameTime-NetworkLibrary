package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthResponse
import com.example.gametime_networklibrary.domain.repository.UserRepository

class UserRepositoryImpl(private val api: UserService) : UserRepository {
    override suspend fun authorization(authRequest: AuthRequest): NetworkResult<AuthResponse> {
        return ApiCallAdapter.safeApiCall { api.authorization(authRequest) }
    }
}