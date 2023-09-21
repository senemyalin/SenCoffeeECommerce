package com.senemyalin.sencoffee.data.source.remote

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.Product

interface RemoteDataSource {

    suspend fun getAllProducts(): NetworkResponse<List<Product>>
    suspend fun getSaleProducts(): NetworkResponse<List<Product>>
    suspend fun getCartProducts(userId: String): NetworkResponse<List<Product>>
    suspend fun searchProduct(searchKey: String): NetworkResponse<List<Product>>
    suspend fun getProductDetailsById(productId: String): NetworkResponse<Product>
    suspend fun addToCard(addToCartRequest: AddToCartRequest): NetworkResponse<Int>
    suspend fun clearCart(): NetworkResponse<Int>
    suspend fun deleteFromCart(deleteFromCartRequest: DeleteFromCartRequest): NetworkResponse<Int>

}