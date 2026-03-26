package com.example.gametime_networklibrary.data.dto

data class ApiError(
    val status: Int,
    val message: String,
    val data: Map<String,ErrorBody>?
)
