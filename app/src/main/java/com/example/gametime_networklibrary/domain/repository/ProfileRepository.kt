package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import com.example.gametime_networklibrary.domain.entities.User
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ProfileRepository {
    suspend fun getProfile(token: String): NetworkResult<User>
    suspend fun updateProfile(token: String,name: RequestBody,avatar: MultipartBody.Part? ):  NetworkResult<UpdateProfile>

}