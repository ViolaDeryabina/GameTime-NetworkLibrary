package com.example.gametime_networklibrary.data.dataSources

import android.os.Message

open class NetworkResult<out T> {
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error(val status: Int, val message: String?): NetworkResult<Nothing>()
    data class Exception(val e: String?): NetworkResult<Nothing>()
}

fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.Error -> NetworkResult.Error(status, message)
        is NetworkResult.Exception -> NetworkResult.Exception(e)
        else -> NetworkResult.Exception("Unknown state")
    }
}
