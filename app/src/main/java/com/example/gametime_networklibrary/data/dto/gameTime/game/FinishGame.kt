package com.example.gametime_networklibrary.data.dto.gameTime.game

data class FinishGame(
    val id: Int,
    val type: String,
    val status: String,
    val image_url: String,
    val target_avatar_url: String,
    val config: Config?,
    val players: List<Creator>
)
