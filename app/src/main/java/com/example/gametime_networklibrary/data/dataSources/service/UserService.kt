package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.auth.Record
import com.example.gametime_networklibrary.data.dto.registration.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("collections/players/auth-with-password")
    suspend fun authorization(@Body authRequest: AuthRequest): AuthResponse

    @POST("collections/players/records")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Record
}