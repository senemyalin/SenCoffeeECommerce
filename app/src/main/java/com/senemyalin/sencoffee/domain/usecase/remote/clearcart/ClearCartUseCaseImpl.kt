package com.senemyalin.sencoffee.domain.usecase.remote.clearcart

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClearCartUseCaseImpl @Inject constructor(
    private val repository: Repository
) : ClearCartUseCase {

    override fun invoke(userId: String): Flow<NetworkResponse<Int>> =
        repository.clearCart(userId)
}