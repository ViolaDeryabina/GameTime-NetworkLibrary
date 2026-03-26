package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.user.UpdateProfileRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.user.auth.Player
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest

interface UserRepository {
    suspend fun authorization(authRequest: AuthRequest): NetworkResult<AuthResponse>
    suspend fun registration(registrationRequest: RegistrationRequest): NetworkResult<Player>

    suspend fun getInformationProfile(token: String, id: String): NetworkResult<Player>

    suspend fun updateProfile(token: String, id: String, updateProfileRequest: UpdateProfileRequest): NetworkResult<Player>



}