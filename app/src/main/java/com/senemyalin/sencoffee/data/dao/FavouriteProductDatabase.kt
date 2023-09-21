package com.senemyalin.sencoffee.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.senemyalin.sencoffee.data.dto.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class FavouriteProductDatabase: RoomDatabase() {

    abstract fun favouriteProductDao(): FavouriteProductDao
}