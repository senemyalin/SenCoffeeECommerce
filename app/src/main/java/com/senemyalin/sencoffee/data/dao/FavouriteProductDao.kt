package com.senemyalin.sencoffee.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Query("SELECT * FROM favourite_product ORDER BY id ASC")
    fun getAllProductsFromFavourite(): Flow<List<Product>>

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM favourite_product")
    suspend fun deleteAllProducts()
}