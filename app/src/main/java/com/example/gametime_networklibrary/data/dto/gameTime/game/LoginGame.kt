package com.example.gametime_networklibrary.data.dto.gameTime.game

data class LoginGame(
    val id: Int,
    val type: String,
    val status: String,
    val max_players: Int,
    val image_url: String,
    val target_avatar_url: String,
    val config: Config?,
    val scheduled_at: String,
    val created_at: String,
    val updated_at: String,
    val players: List<Creator>
)
