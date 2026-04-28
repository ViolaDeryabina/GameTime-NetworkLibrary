package com.example.gametime_networklibrary.data.dto.gameTime.game

import com.example.gametime_networklibrary.data.dto.gameTime.authentication.UserDto
import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Int,
    val type: String,
    val status: String,
    @SerializedName("creator_id")
    val creatorId: Int,
    @SerializedName("max_players")
    val maxPlayers: Int,
    val difficulty: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("target_avatar_url")
    val targetAvatarUrl: String,
    val config: Config,
    @SerializedName("winner_id")
    val winnerId: Int,
    @SerializedName("winner_time")
    val winnerTime: Int,
    @SerializedName("scheduled_at")
    val scheduledAt: String,
    @SerializedName("started_at")
    val startedAt: String,
    @SerializedName("finished_at")
    val finishedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val creator: UserDto,
    val players: List<UserDto>
)
