package com.example.gametime_networklibrary.data.dataSources.module

import com.example.gametime_networklibrary.data.repositoryImpl.StatisticsRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.StatisticsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StatisticsModule {

    @Binds
    @Singleton
    abstract fun statisticsService(statisticsRepositoryImpl: StatisticsRepositoryImpl): StatisticsRepository
}