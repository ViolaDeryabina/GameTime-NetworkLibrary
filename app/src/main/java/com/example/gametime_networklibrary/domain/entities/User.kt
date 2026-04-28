package com.example.gametime_networklibrary.domain.entities

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String?,
    val isOnline: Boolean
)
