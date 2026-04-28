package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.game.CreatorDto
import com.example.gametime_networklibrary.domain.entities.Participant

// Маппер для участника (создателя/игрока)
fun CreatorDto.toDomain(): Participant {
    return Participant(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatar,
        isOnline = this.is_online,
        email = this.email
    )
}
