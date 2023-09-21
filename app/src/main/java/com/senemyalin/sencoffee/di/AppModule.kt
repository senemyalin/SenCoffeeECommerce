package com.senemyalin.sencoffee.di

import com.senemyalin.sencoffee.common.Constants
import com.senemyalin.sencoffee.data.api.CoffeeApi
import com.senemyalin.sencoffee.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(Constants.BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): CoffeeApi {
        return retrofit.create(CoffeeApi::class.java)
    }
}