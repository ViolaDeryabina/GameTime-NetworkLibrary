package com.example.gametime_networklibrary.data.repositoryImpl

import com.example.gametime_networklibrary.data.dataSources.ApiCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dataSources.map
import com.example.gametime_networklibrary.data.dataSources.service.ProfileService
import com.example.gametime_networklibrary.data.dto.gameTime.profile.UpdateProfile
import com.example.gametime_networklibrary.data.mappers.toDomain
import com.example.gametime_networklibrary.domain.entities.User
import com.example.gametime_networklibrary.domain.repository.ProfileRepository
import jakarta.inject.Inject
import okhttp3.MultipartBody
import okhttp3.RequestBody


class ProfileRepositoryImpl  @Inject constructor
    (private val api: ProfileService) : ProfileRepository {
    override suspend fun getProfile(token: String): NetworkResult<User> {
        return ApiCallAdapter.safeApiCall {
            api.getProfile("Bearer $token")
        }
            .map { it.toDomain() }
    }

    override suspend fun updateProfile(
        token: String,
        name: RequestBody,
        avatar: MultipartBody.Part?
    ): NetworkResult<UpdateProfile> {
        return ApiCallAdapter.safeApiCall { api.updateProfile("Bearer ${token}",name,avatar) }
    }
}