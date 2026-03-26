package com.example.gametime_networklibrary.data.dto.game

data class CategoryList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<CategoryItems>
)
