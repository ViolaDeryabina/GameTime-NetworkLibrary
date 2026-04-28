package com.example.gametime_networklibrary.data.mappers

import com.example.gametime_networklibrary.data.dto.gameTime.game.GameListDto
import com.example.gametime_networklibrary.domain.entities.GameList

fun GameListDto.toDomain(): GameList{
    return GameList(
        currentPage = this.currentPage,
        data = this.data,
        firstPageUrl = this.firstPageUrl,
        from = this.from,
        lastPage = this.lastPage,
        lastPageUrl = this.lastPageUrl,
        perPage = this.perPage,
        to = this.to,
        total = this.total
    )
}