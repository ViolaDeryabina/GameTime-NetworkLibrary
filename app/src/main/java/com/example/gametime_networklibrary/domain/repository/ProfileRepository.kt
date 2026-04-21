package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import retrofit2.http.Body
import retrofit2.http.Header

interface ProfileRepository {
    suspend fun getProfile(token: String): NetworkResult<User>
    suspend fun updateProfile(token: String,updateProfile: UpdateProfile ): NetworkResult<UpdateProfile>

}