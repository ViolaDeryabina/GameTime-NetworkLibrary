package com.example.gametime_networklibrary.data.dataSources

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.gametime_networklibrary.data.repositoryImpl.ApolloRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Apollo {
    private const val BASE_URL = "https://oidmhqpxptfyjvkejmat.supabase.co/graphql/v1"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(okHttpClient)
        .build()

    val apolloService = ApolloRepositoryImpl(apolloClient)
}