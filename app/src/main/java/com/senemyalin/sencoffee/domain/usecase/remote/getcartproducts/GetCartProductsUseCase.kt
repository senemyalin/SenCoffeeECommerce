package com.senemyalin.sencoffee.domain.usecase.remote.getcartproducts

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface GetCartProductsUseCase {
    operator fun invoke(userId: String): Flow<NetworkResponse<List<Product>>>
}