package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.game.GameDto
import com.example.gametime_networklibrary.domain.entities.Game

fun GameDto.toDomain(): Game{
    return Game(
        id = this.id,
        type = this.type,
        status = this.status,
        maxPlayers = this.max_players,
        difficulty = this.difficulty,
        imageUrl = this.image_url,
        targetAvatarUrl = this.target_avatar_url,
        config = this.config,
        scheduledAt = this.scheduled_at,
        creator = this.creator.toDomain(),
        players = this.players.map { it.toDomain()}
    )
}