package com.example.gametime_networklibrary.data.dataSources.module

import com.example.gametime_networklibrary.data.repositoryImpl.UserRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {
    @Binds
    @Singleton
    abstract fun bindUserService(userRepositoryImpl: UserRepositoryImpl): UserRepository

}