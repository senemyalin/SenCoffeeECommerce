package com.senemyalin.sencoffee.di

import android.content.Context
import androidx.room.Room
import com.senemyalin.sencoffee.data.dao.favouriteProductDao
import com.senemyalin.sencoffee.data.dao.favouriteProductDatabase
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
    ): favouriteProductDatabase =
        Room.databaseBuilder(
            context,
            favouriteProductDatabase::class.java,
            "favourite_product"
        ).build()

    @Provides
    fun provideProductDao(database: favouriteProductDatabase): favouriteProductDao =
        database.favouriteProductDao()

}