package com.example.gametime_networklibrary.data.dto.game

import com.example.gametime_networklibrary.data.dto.user.auth.Player

data class Expand(
    val category: CategoryItems,
    val host: Player
)
