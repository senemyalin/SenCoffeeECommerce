package com.senemyalin.sencoffee.data.source.local

import com.senemyalin.sencoffee.data.dao.FavouriteProductDao
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val favouriteProductDao: FavouriteProductDao
) : LocalDataSource {

    override suspend fun addProduct(product: Product) =
        favouriteProductDao.addProduct(product)

    override suspend fun deleteProduct(product: Product) =
        favouriteProductDao.deleteProduct(product)

    override suspend fun deleteAllProducts() =
        favouriteProductDao.deleteAllProducts()

    override fun getAllProductsFromFavourite(): Flow<List<Product>> =
        favouriteProductDao.getAllProductsFromFavourite()

}
