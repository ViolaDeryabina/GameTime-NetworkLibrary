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
                    .httpHeaders(listOf(
                        HttpHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9pZG1ocXB4cHRmeWp2a2VqbWF0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzQ4ODU0NjgsImV4cCI6MjA5MDQ2MTQ2OH0.zsXilXjE9eRLlbYUSzGcMUHRFwQRR_bgtghYyduZh_g"),
                        HttpHeader("Authorisation", "Bearer $token"),
                        ))
                    .execute()
            },
            mapper = { it.toDomain() }
        )
    }
}