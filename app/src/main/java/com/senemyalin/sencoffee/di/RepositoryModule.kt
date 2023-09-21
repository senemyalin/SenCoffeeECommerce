package com.senemyalin.sencoffee.di

import com.senemyalin.sencoffee.data.repository.RepositoryImpl
import com.senemyalin.sencoffee.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}