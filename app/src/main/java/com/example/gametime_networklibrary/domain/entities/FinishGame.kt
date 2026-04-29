package com.example.gametime_networklibrary.domain.entities

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.example.gametime_networklibrary.data.dto.gameTime.game.Config

data class FinishGame(
    val id: Int,
    val type: String,
    val status: String,
    val imageUrl: String,
    val targetAvatarUrl: String,
    val config: Config?,
    val players: List<User>
)
