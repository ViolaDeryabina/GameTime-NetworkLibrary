package com.example.gametime_networklibrary.data.dto.gameTime.authentication

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("is_online")
    val isOnline: Boolean
)
