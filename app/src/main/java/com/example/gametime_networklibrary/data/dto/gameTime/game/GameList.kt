package com.example.gametime_networklibrary.data.dto.gameTime.game

data class GameList(
    val current_page: Int,
    val data: List<Game>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)
