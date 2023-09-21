package com.senemyalin.sencoffee.domain.usecase.remote.getsaleproducts

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSaleProductsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetSaleProductsUseCase {

    override fun invoke(): Flow<NetworkResponse<List<Product>>> =
        repository.getSaleProducts()

}