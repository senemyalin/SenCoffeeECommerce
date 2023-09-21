package com.senemyalin.sencoffee.domain.usecase.local.getallfavouriteproducts

import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite.DeleteFromFavouriteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouriteProductsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetAllFavouriteProductsUseCase {

    override suspend fun invoke(): Flow<List<Product>> =
        repository.getAllProductsFromFavourite()
}