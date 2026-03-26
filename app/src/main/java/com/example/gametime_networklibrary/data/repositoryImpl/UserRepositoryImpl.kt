package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.dto.user.UpdateProfileRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.user.auth.Player
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest
import com.example.gametime_networklibrary.domain.repository.UserRepository

class UserRepositoryImpl(private val api: UserService) : UserRepository {

    override suspend fun authorization(authRequest: AuthRequest): NetworkResult<AuthResponse> {
        return ApiCallAdapter.safeApiCall { api.authorization(authRequest) }
    }

    override suspend fun registration(registrationRequest: RegistrationRequest): NetworkResult<Player> {
        return ApiCallAdapter.safeApiCall { api.registration(registrationRequest) }
    }

    override suspend fun getInformationProfile(
        token: String,
        id: String
    ): NetworkResult<Player> {
        return ApiCallAdapter.safeApiCall { api.getInformationProfile("Bearer $token", id) }
    }

    override suspend fun updateProfile(
        token: String,
        id: String,
        updateProfileRequest: UpdateProfileRequest
    ): NetworkResult<Player> {
        return ApiCallAdapter.safeApiCall {
            api.updateProfile(
                "Bearer $token",
                id,
                updateProfileRequest
            )
        }
    }




}