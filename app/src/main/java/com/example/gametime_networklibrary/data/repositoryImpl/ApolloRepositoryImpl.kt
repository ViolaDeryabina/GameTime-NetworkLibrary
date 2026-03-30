package com.example.gametime_networklibrary.data.repositoryImpl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.http.HttpHeader
import com.example.gametime_networklibrary.GetStatisticQuery
import com.example.gametime_networklibrary.data.dataSources.ApolloCallAdapter
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.mapper.toDomain
import com.example.gametime_networklibrary.domain.model.StatisticItem
import com.example.gametime_networklibrary.domain.repository.ApolloRepository

class ApolloRepositoryImpl(val apolloClient: ApolloClient) : ApolloRepository {
    override suspend fun getStatistic(token: String): NetworkResult<List<StatisticItem>> {
        return ApolloCallAdapter.safeApiCall(
            call = {
                apolloClient.query(GetStatisticQuery())
                    .httpHeaders(listOf(HttpHeader("Authorization", "Bearer $token"))).execute()
            },
            mapper = { it.toDomain() }
        )
    }
}