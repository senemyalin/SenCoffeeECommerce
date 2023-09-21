package com.senemyalin.sencoffee.domain.usecase.remote.getcartproducts

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartProductsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetCartProductsUseCase {

    override fun invoke(userId: String): Flow<NetworkResponse<List<Product>>> =
        repository.getCartProducts(userId)
}