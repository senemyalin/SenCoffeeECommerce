package com.senemyalin.sencoffee.domain.usecase.remote.addtocart

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import kotlinx.coroutines.flow.Flow

interface AddToCartUseCase {
    operator fun invoke(addToCartRequest: AddToCartRequest): Flow<NetworkResponse<Int>>
}