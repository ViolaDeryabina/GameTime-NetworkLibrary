package com.example.gametime_networklibrary.data.dto.gameTime.game

data class CreateGameRequest(
    val type: String,
    val scheduled_at: String,
    val title: String,
    val description: String,
    val points: Int,
    val max_players: Int,
    val difficulty: Int
)
