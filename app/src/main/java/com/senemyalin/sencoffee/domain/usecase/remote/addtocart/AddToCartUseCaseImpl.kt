package com.senemyalin.sencoffee.domain.usecase.remote.addtocart

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToCartUseCaseImpl @Inject constructor(
    private val repository: Repository
) : AddToCartUseCase {

    override fun invoke(addToCartRequest: AddToCartRequest): Flow<NetworkResponse<Int>> =
        repository.addToCard(addToCartRequest)
}