package com.senemyalin.sencoffee.domain.usecase.remote.getsaleproducts

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface GetSaleProductsUseCase {
    operator fun invoke(): Flow<NetworkResponse<List<Product>>>
}