package com.example.gametime_networklibrary.data.dataSources.module

import com.example.gametime_networklibrary.data.repositoryImpl.GameRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GameModule {

    @Binds
    @Singleton
    abstract fun bindGameService(gameRepositoryImpl: GameRepositoryImpl): GameRepository
}