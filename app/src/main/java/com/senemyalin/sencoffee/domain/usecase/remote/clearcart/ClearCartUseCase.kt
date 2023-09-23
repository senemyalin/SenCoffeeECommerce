package com.senemyalin.sencoffee.domain.usecase.remote.clearcart

import com.senemyalin.sencoffee.common.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface ClearCartUseCase {
    operator fun invoke(userId: String): Flow<NetworkResponse<Int>>
}