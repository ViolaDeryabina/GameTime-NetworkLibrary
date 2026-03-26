package com.example.gametime_networklibrary.data.dto.user.registration

data class RegistrationRequest(
    val email: String,
    val password: String,
    val passwordConfirm: String,
    val fullName: String,
    val userName: String,
    val phone: String
)
