package com.senemyalin.sencoffee.domain.usecase.remote.getproductdetailsbyid

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface GetProductDetailsByIdUseCase {
    operator fun invoke(productId: String): Flow<NetworkResponse<Product>>
}