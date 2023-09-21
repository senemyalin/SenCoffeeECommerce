package com.senemyalin.sencoffee.data.source.local

import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun addProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun deleteAllProducts()
    fun getAllProductsFromFavourite(): Flow<List<Product>>
}