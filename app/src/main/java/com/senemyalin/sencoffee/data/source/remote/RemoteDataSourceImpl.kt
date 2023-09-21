package com.senemyalin.sencoffee.data.source.remote

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.api.CoffeeApi
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val coffeeApi: CoffeeApi
) : RemoteDataSource {

    override suspend fun getAllProducts(): NetworkResponse<List<Product>> =
        try {
            val response = coffeeApi.getAllProducts()
            if (response.message == "Success") {
                NetworkResponse.Success(response.products)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun getSaleProducts(): NetworkResponse<List<Product>> =
        try {
            val response = coffeeApi.getSaleProducts()
            if (response.message == "Success") {
                NetworkResponse.Success(response.products)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun getCartProducts(userId: String): NetworkResponse<List<Product>> =
        try {
            val response = coffeeApi.getCartProducts(userId)
            if (response.message == "Success") {
                NetworkResponse.Success(response.products)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun searchProduct(searchKey: String): NetworkResponse<List<Product>> =
        try {
            val response = coffeeApi.searchProduct(searchKey)
            if (response.message == "Success") {
                NetworkResponse.Success(response.products)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun getProductDetailsById(productId: String): NetworkResponse<Product> =
        try {
            val response = coffeeApi.getProductDetailsById(productId)
            if (response.message == "Success") {
                NetworkResponse.Success(response.product)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun addToCard(addToCartRequest: AddToCartRequest): NetworkResponse<Int> =
        try {
            val response = coffeeApi.addToCard(addToCartRequest)
            if (response.status == 200) {
                NetworkResponse.Success(response.status)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun clearCart(): NetworkResponse<Int> =
        try {
            val response = coffeeApi.clearCart()
            if (response.status == 200) {
                NetworkResponse.Success(response.status)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

    override suspend fun deleteFromCart(deleteFromCartRequest: DeleteFromCartRequest): NetworkResponse<Int> =
        try {
            val response = coffeeApi.deleteFromCart(deleteFromCartRequest)
            if (response.status == 200) {
                NetworkResponse.Success(response.status)
            } else {
                NetworkResponse.Error(Exception(response.message))
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e)
        }

}