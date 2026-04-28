package com.example.gametime_networklibrary.data.dto.gameTime.authentication

data class RegisterResponse(
    val message: String,
    val user: UserDto,
    val token: String
)
