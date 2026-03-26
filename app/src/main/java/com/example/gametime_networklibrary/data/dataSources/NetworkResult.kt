package com.example.gametime_networklibrary.data.dataSources

import android.os.Message

open class NetworkResult<out T> {
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error(val status: Int, val message: String?): NetworkResult<Nothing>()
    data class Exception(val e: String?): NetworkResult<Nothing>()
}