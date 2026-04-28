package com.example.gametime_networklibrary.data.dataSources

import android.util.Log
import com.example.gametime_networklibrary.data.dataSources.service.GameService
import com.example.gametime_networklibrary.data.dataSources.service.ProfileService
import com.example.gametime_networklibrary.data.dataSources.service.StatisticsService
import com.example.gametime_networklibrary.data.dataSources.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://game2026.skillmad.ru/api/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("NetworkService", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)


    @Provides
    @Singleton
    fun provideGameService(retrofit: Retrofit): GameService =
        retrofit.create(GameService::class.java)


    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)


    @Provides
    @Singleton
    fun provideStatisticsService(retrofit: Retrofit): StatisticsService =
        retrofit.create(StatisticsService::class.java)


}