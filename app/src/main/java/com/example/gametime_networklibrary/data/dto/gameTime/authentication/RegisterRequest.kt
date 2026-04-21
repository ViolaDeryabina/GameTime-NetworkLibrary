package com.example.gametime_networklibrary.data.dto.gameTime.authentication

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val firstname: String,
    val lastname: String,
    val phone: String
)
