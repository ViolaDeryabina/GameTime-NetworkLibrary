package com.example.gametime_networklibrary.data.dataSources

import android.util.Log
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.exception.ApolloNetworkException
import okio.IOException

object ApolloCallAdapter {
    suspend fun <D : Operation.Data, T> safeApiCall(
        call: suspend () -> ApolloResponse<D>,
        mapper: (D) -> T
    ): NetworkResult<T> {
        return try {
            val response = call()
            val error = response.errors?.firstOrNull()
            val data = response.data

            when {
                error != null -> {
                    Log.e("ApolloCallAdapter", "GraphQL Error: ${error.message}")
                    NetworkResult.Error(400, error.message)
                }

                data != null -> {
                    Log.d("ApolloCallAdapter", "GraphQL Success")
                    NetworkResult.Success(mapper(data))
                }

                else -> {
                    Log.e("ApolloCallAdapter", "GraphQL Error: Empty response")
                    NetworkResult.Error(404, "Empty response")
                }
            }
        } catch (e: Throwable) {
            val message = if (e is ApolloNetworkException || e is IOException)
                "Нет интернета" else e.localizedMessage
            Log.e("ApolloCallAdapter", "GraphQL Error: $message")
            NetworkResult.Exception(message)
        }
    }
}
