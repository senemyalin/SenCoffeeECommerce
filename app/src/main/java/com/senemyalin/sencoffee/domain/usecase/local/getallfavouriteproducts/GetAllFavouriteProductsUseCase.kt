package com.senemyalin.sencoffee.domain.usecase.local.getallfavouriteproducts

import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface GetAllFavouriteProductsUseCase {
    suspend operator fun invoke(): Flow<List<Product>>
}