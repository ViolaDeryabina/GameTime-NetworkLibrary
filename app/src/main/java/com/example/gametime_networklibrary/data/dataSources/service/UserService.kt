package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.user.UpdateProfileRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.user.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.user.auth.Player
import com.example.gametime_networklibrary.data.dto.user.registration.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("collections/players/auth-with-password")
    suspend fun authorization(@Body authRequest: AuthRequest): AuthResponse

    @POST("collections/players/records")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Player

    @GET("collections/players/records/{id}")
    suspend fun getInformationProfile(@Header("Authorization") token: String, @Path("id") id: String): Player

    @PATCH("collections/players/records/{id}")
    suspend fun updateProfile(@Header("Authorization") token: String, @Path("id") id: String, @Body updateProfileRequest: UpdateProfileRequest): Player

}