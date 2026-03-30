package com.example.gametime_networklibrary.domain.repository

import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.domain.model.StatisticItem

interface ApolloRepository {
    suspend fun getStatistic(token: String): NetworkResult<List<StatisticItem>>
}