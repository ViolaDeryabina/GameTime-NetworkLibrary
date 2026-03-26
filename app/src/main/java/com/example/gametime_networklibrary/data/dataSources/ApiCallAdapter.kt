package com.example.gametime_networklibrary.data.dataSources

import android.util.Log
import com.example.gametime_networklibrary.data.dto.ApiError
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

object ApiCallAdapter {
    suspend fun <T>safeApiCall(call: suspend ()->T): NetworkResult<T>{
        return try {
            Log.i("ApiCallAdapter", "Success result")
            NetworkResult.Success(call())
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.toString()
            val errorMessage = try {
                val error = Gson().fromJson(errorBody, ApiError::class.java)
                error.message
            }catch (_: Exception){
                e.message
            }
            Log.e("ApiCallAdapter", "${e.code()}: $errorMessage")
            NetworkResult.Error(e.code(),errorMessage)
        }catch (e: IOException){
            Log.e("ApiCallAdapter", "${e.message}")
            NetworkResult.Exception(e.message)
        }
        catch (e: Throwable){
            Log.e("ApiCallAdapter", "${e.message}")
            NetworkResult.Exception(e.message)
        }
    }
}