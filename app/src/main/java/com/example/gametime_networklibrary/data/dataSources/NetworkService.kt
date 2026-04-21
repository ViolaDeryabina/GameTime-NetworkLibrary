package com.example.gametime_networklibrary.data.dataSources

import android.util.Log
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dataSources.service.ProfileService
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import com.example.gametime_networklibrary.data.repositoryImpl.GameRepositoryImpl
import com.example.gametime_networklibrary.data.repositoryImpl.ProfileRepositoryImpl
import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.ProfileRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "https://game2026.skillmad.ru/api/"

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("NetworkService", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val userService = retrofit.create(UserService::class.java)
    private val profileService = retrofit.create(ProfileService::class.java)
    private val gameService = retrofit.create(GameService::class.java)

    val userRepository = UserRepositoryImpl(userService)
    val profileRepository = ProfileRepositoryImpl(profileService)
    val gameRepository = GameRepositoryImpl(gameService)

}