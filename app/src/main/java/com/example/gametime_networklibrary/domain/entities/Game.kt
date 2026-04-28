package com.example.gametime_networklibrary.domain.entities

import com.example.gametime_networklibrary.data.dto.gameTime.game.Config
import com.example.gametime_networklibrary.data.dto.gameTime.game.CreatorDto

data class Game(
    val id: Int,
    val type: String,
    val status: String,
    val maxPlayers: Int,
    val difficulty: Int,
    val imageUrl: String,
    val targetAvatarUrl: String,
    val config: Config,
    val scheduledAt: String,
    val creator: User,
    val players: List<User>
)
