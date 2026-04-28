package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.example.gametime_networklibrary.domain.entities.User

fun UserDto.toDomain(): User{
    return User(
        id = this.id,
        name = this.name,
        email = this.email,
        avatarUrl = this.avatar,
        isOnline = this.isOnline
    )
}