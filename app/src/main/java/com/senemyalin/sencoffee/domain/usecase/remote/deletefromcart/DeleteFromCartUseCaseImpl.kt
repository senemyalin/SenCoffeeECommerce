package com.senemyalin.sencoffee.domain.usecase.remote.deletefromcart

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteFromCartUseCaseImpl @Inject constructor(
    private val repository: Repository
) : DeleteFromCartUseCase {

    override fun invoke(deleteFromCartRequest: DeleteFromCartRequest): Flow<NetworkResponse<Int>> =
        repository.deleteFromCart(deleteFromCartRequest)
}