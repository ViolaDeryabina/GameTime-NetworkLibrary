package com.example.gametime_networklibrary.data.dto.gameTime.profile

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.User

data class UpdateProfile(
    val message: String,
    val user: User
)
