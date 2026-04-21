package com.example.gametime_networklibrary.data.dto.gameTime.game

data class Game(
    val id: Int,
    val type: String,
    val status: String,
    val creator_id: Int,
    val max_players: Int,
    val difficulty: Int,
    val image_url: String,
    val target_avatar_url: String,
    val config: Config,
    val winner_id: Int,
    val winner_time: Int,
    val scheduled_at: String,
    val started_at: String,
    val finished_at: String,
    val created_at: String,
    val updated_at: String,
    val creator: Creator,
    val players: List<Creator>
)
