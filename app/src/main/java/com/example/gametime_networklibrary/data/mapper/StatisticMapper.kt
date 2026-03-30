package com.example.gametime_networklibrary.data.mapper

import com.example.gametime_networklibrary.GetStatisticQuery
import com.example.gametime_networklibrary.domain.model.StatisticItem

fun GetStatisticQuery.Data.toDomain(): List<StatisticItem> {
    return this.statisticCollection?.edges?.map { it.node.toDomain() } ?: emptyList()
}

private fun GetStatisticQuery.Node.toDomain(): StatisticItem {
    return StatisticItem(
        id = nodeId,
        total = total ?: 0,
        winImage = winimage ?: 0,
        winCircle = wincircle ?: 0
    )
}