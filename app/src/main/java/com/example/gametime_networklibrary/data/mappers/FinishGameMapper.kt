package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.game.FinishGameDto
import com.example.gametime_networklibrary.domain.entities.FinishGame

fun FinishGameDto.toDomain(): FinishGame{
    return FinishGame(
        id = this.id,
        type = this.type,
        status = this.status,
        imageUrl = this.imageUrl,
        targetAvatarUrl = this.targetAvatarUrl,
        config = this.config,
        players = this.players.map { it.toDomain() }
    )
}