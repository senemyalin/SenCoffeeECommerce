package com.senemyalin.sencoffee.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface Repository {

    //Remote
    fun getAllProducts(): Flow<NetworkResponse<List<Product>>>
    fun getSaleProducts(): Flow<NetworkResponse<List<Product>>>
    fun getCartProducts(userId: String): Flow<NetworkResponse<List<Product>>>
    fun searchProduct(searchKey: String): Flow<NetworkResponse<List<Product>>>
    fun getProductDetailsById(productId: String): Flow<NetworkResponse<Product>>
    fun addToCard(addToCartRequest: AddToCartRequest): Flow<NetworkResponse<Int>>
    fun clearCart(userId: String): Flow<NetworkResponse<Int>>
    fun deleteFromCart(deleteFromCartRequest: DeleteFromCartRequest): Flow<NetworkResponse<Int>>

    //Local
    suspend fun addProduct(product: Product)
    fun getAllProductsFromFavourite(): Flow<List<Product>>
    suspend fun deleteProduct(product: Product)
    suspend fun deleteAllProducts()
}