package com.example.gametime_networklibrary.data.dto.game

import com.google.gson.annotations.SerializedName

data class GameRecord(
    val id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val updated: String,
    val gameName: String,
    val category: String,
    val winningPrice: Int,
    val from: String,
    val to: String,
    val description: String,
    val notification: Boolean,
    val host: String,
    val expand: Expand?
)
