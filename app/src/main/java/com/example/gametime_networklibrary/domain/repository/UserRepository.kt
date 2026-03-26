package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.auth.Record
import com.example.gametime_networklibrary.data.dto.registration.RegistrationRequest
import retrofit2.http.Body

interface UserRepository {
    suspend fun authorization(authRequest: AuthRequest): NetworkResult<AuthResponse>
    suspend fun registration(registrationRequest: RegistrationRequest): NetworkResult<Record>

}