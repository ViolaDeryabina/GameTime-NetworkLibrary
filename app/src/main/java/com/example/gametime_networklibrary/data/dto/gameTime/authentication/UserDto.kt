package com.example.gametime_networklibrary.data.dto.gameTime.authentication

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    val email_verified_at: String,
    val created_at: String,
    val updated_at: String,
    val is_online: Boolean
)
