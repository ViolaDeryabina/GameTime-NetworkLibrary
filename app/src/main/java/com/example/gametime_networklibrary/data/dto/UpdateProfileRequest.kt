package com.example.gametime_networklibrary.data.dto

data class UpdateProfileRequest(
    val fullName: String,
    val userName: String,
    val phone: String,
    val photo: String?
)
