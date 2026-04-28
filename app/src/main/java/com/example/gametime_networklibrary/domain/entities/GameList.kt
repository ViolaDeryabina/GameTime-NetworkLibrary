package com.example.gametime_networklibrary.domain.entities

import com.example.gametime_networklibrary.data.dto.gameTime.game.GameDto

data class GameList(
    val currentPage: Int,
    val data: List<GameDto>,
    val firstPageUrl: String,
    val from: Int,
    val lastPage: Int,
    val lastPageUrl: String,
    val perPage: Int,
    val to: Int,
    val total: Int
)

