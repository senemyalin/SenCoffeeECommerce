package com.senemyalin.sencoffee.domain.usecase.remote.searchproduct

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface SearchProductUseCase {
    operator fun invoke(searchKey: String): Flow<NetworkResponse<List<Product>>>
}