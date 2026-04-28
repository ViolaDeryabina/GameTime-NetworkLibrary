package com.example.gametime_networklibrary.domain.entities

data class Participant(
    val id: Int,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val isOnline: Boolean
)
