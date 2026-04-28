package com.example.gametime_networklibrary.data.dataSources.service

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import com.example.gametime_networklibrary.domain.entities.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ProfileService {
    @GET("profile")
    suspend fun getProfile(@Header("Authorization")token: String): UserDto

    @Multipart
    @PUT("profile")
    suspend fun updateProfile(@Header("Authorization")token: String, @Part("name") name: RequestBody, @Part avatar: MultipartBody.Part? ): UpdateProfile
}