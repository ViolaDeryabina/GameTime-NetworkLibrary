package com.example.gametime_networklibrary.data.dto.game

data class CreateGameRequest(
    val gameName: String,
    val category: String,
    val winningPrice: Int,
    val from: String,
    val to: String,
    val description: String,
    val notification: Boolean,
    val host: String
)
