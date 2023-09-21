package com.senemyalin.sencoffee.domain.usecase.remote.deletefromcart

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import kotlinx.coroutines.flow.Flow

interface DeleteFromCartUseCase {
    operator fun invoke(deleteFromCartRequest: DeleteFromCartRequest): Flow<NetworkResponse<Int>>
}