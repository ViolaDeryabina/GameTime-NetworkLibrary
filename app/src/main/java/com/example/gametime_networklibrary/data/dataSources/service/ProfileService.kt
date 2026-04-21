package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileService {
    @GET("profile")
    suspend fun getProfile(@Header("Authorization")token: String): User

    @PUT("profile")
    suspend fun updateProfile(@Header("Authorization")token: String,@Body updateProfile: UpdateProfile ): UpdateProfile
}