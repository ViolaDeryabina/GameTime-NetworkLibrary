package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.game.GameDto
import com.example.gametime_networklibrary.domain.entities.Game

fun GameDto.toDomain(): Game{
    return Game(
        id = this.id,
        type = this.type,
        status = this.status,
        maxPlayers = this.maxPlayers,
        difficulty = this.difficulty,
        imageUrl = this.imageUrl,
        targetAvatarUrl = this.targetAvatarUrl,
        config = this.config,
        scheduledAt = this.scheduledAt,
        creator = this.creator.toDomain(),
        players = this.players.map { it.toDomain()}
    )
}