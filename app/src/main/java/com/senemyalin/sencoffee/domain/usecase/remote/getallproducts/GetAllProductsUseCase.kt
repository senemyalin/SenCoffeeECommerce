package com.senemyalin.sencoffee.domain.usecase.remote.getallproducts

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {
    operator fun invoke(): Flow<NetworkResponse<List<Product>>>
}