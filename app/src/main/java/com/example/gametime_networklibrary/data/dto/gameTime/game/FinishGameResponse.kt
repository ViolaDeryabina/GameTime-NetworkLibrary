package com.example.gametime_networklibrary.data.dto.gameTime.game

data class FinishGameResponse(
    val message: String,
    val game: FinishGameDto,
    val your_time: Int,
    val waiting_for_players: Boolean
)
