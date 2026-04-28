package com.example.gametime_networklibrary.data.dataSources.module

import com.example.gametime_networklibrary.data.repositoryImpl.ProfileRepositoryImpl
import com.example.gametime_networklibrary.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    @Singleton
    abstract fun bindProfileService(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}