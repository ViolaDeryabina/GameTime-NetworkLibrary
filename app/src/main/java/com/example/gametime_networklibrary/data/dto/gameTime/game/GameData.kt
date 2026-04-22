package com.example.gametime_networklibrary.data.dto.gameTime.game

data class GameData(
    val data: Data
)

data class Data(
    val image_puzzle: Int,
    val ring_puzzle: Int,
    val total: Int
)
