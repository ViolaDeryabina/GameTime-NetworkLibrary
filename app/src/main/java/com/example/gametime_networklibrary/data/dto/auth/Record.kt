package com.example.gametime_networklibrary.data.dto.auth

data class Record(
    val collectionId: String,
    val collectionName: String,
    val id: String,
    val email: String,
    val emailVisibility: Boolean,
    val verified: Boolean,
    val fullName: String,
    val userName: String,
    val phone: String,
    val photo: String,
    val created: String,
    val updated: String
)
