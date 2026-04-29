package com.example.gametime_networklibrary.data.dto.gameTime.game

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.google.gson.annotations.SerializedName

data class FinishGameDto(
    val id: Int,
    val type: String,
    val status: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("target_avatar_url")
    val targetAvatarUrl: String,
    val config: Config?,
    val players: List<UserDto>
)
