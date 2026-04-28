package com.example.gametime_networklibrary.data.dto.gameTime.game

import com.google.gson.annotations.SerializedName

data class CreatorDto(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String,
    @SerializedName("is_online")
    val isOnline: Boolean
)
