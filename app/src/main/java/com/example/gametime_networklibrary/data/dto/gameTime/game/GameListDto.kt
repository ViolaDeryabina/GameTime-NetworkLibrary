package com.example.gametime_networklibrary.data.dto.gameTime.game

import com.google.gson.annotations.SerializedName

data class GameListDto(
    @SerializedName("current_page")
    val currentPage: Int,
    val data: List<GameDto>,
    @SerializedName("first_page_url")
    val firstPageUrl: String,
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    @SerializedName("last_page_url")
    val lastPageUrl: String,
    @SerializedName("per_page")
    val perPage: Int,
    val to: Int,
    val total: Int
)
