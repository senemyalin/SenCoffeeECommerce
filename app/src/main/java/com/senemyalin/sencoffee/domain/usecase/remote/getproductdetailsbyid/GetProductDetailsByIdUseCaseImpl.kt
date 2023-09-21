package com.senemyalin.sencoffee.domain.usecase.remote.getproductdetailsbyid

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsByIdUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetProductDetailsByIdUseCase {

    override fun invoke(productId: String): Flow<NetworkResponse<Product>> =
        repository.getProductDetailsById(productId)

}