package com.senemyalin.sencoffee.di

import android.content.Context
import androidx.room.Room
import com.senemyalin.sencoffee.data.dao.FavouriteProductDao
import com.senemyalin.sencoffee.data.dao.FavouriteProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteProductDaoModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FavouriteProductDatabase =
        Room.databaseBuilder(
            context,
            FavouriteProductDatabase::class.java,
            "favourite_product"
        ).build()

    @Provides
    fun provideProductDao(database: FavouriteProductDatabase): FavouriteProductDao =
        database.favouriteProductDao()

}