package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.UpdateProfileRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthRequest
import com.example.gametime_networklibrary.data.dto.auth.AuthResponse
import com.example.gametime_networklibrary.data.dto.auth.Record
import com.example.gametime_networklibrary.data.dto.registration.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UserService {
    @POST("collections/players/auth-with-password")
    suspend fun authorization(@Body authRequest: AuthRequest): AuthResponse

    @POST("collections/players/records")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): Record

    @GET("collections/players/records/{id}")
    suspend fun getInformationProfile(@Header("Authorization") token: String, @Path("id") id: String): Record

    @PATCH("collections/players/records/{id}")
    suspend fun updateProfile(@Header("Authorization") token: String, @Path("id") id: String, @Body updateProfileRequest: UpdateProfileRequest): Record

}