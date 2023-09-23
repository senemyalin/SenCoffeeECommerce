package com.senemyalin.sencoffee.data.repository

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.data.source.local.LocalDataSource
import com.senemyalin.sencoffee.data.source.remote.RemoteDataSource
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override fun getAllProducts(): Flow<NetworkResponse<List<Product>>> =
        flow {
            try {
                when (val response = remoteDataSource.getAllProducts()) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun getSaleProducts(): Flow<NetworkResponse<List<Product>>> =
        flow {
            try {
                when (val response = remoteDataSource.getSaleProducts()) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun getCartProducts(userId: String): Flow<NetworkResponse<List<Product>>> =
        flow {
            try {
                when (val response = remoteDataSource.getCartProducts(userId)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun searchProduct(searchKey: String): Flow<NetworkResponse<List<Product>>> =
        flow {
            try {
                when (val response = remoteDataSource.searchProduct(searchKey)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> {} //TODO
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun getProductDetailsById(productId: String): Flow<NetworkResponse<Product>> =
        flow {
            try {
                when (val response = remoteDataSource.getProductDetailsById(productId)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun addToCard(addToCartRequest: AddToCartRequest): Flow<NetworkResponse<Int>> =
        flow {
            try {
                when (val response = remoteDataSource.addToCard(addToCartRequest)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun clearCart(): Flow<NetworkResponse<Int>> =
        flow {
            try {
                when (val response = remoteDataSource.clearCart()) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override fun deleteFromCart(deleteFromCartRequest: DeleteFromCartRequest): Flow<NetworkResponse<Int>> =
        flow {
            try {
                when (val response = remoteDataSource.deleteFromCart(deleteFromCartRequest)) {
                    is NetworkResponse.Error -> emit(response)
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> emit(response)
                }
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e))
            }
        }

    override suspend fun addProduct(product: Product) =
        withContext(Dispatchers.IO) {
            try {
                localDataSource.addProduct(product)
            } catch (e: Exception) {
                println("LocalDatabase addProduct Function Exception: ${e.localizedMessage}")
            }
        }

    override fun getAllProductsFromFavourite(): Flow<List<Product>> =
        localDataSource.getAllProductsFromFavourite()

    override suspend fun deleteProduct(product: Product) =
        withContext(Dispatchers.IO) {
            try {
                localDataSource.deleteProduct(product)
            } catch (e: Exception) {
                println("LocalDatabase deleteProduct Function Exception: ${e.localizedMessage}")
            }
        }

    override suspend fun deleteAllProducts() =
        withContext(Dispatchers.IO) {
            try {
                localDataSource.deleteAllProducts()
            } catch (e: Exception) {
                println("LocalDatabase deleteAllProduct Function Exception: ${e.localizedMessage}")
            }
        }
}