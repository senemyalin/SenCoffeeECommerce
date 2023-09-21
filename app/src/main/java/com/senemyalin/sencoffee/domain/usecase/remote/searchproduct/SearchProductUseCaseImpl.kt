package com.senemyalin.sencoffee.domain.usecase.remote.searchproduct

import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductUseCaseImpl @Inject constructor(
    private val repository: Repository
) : SearchProductUseCase {

    override fun invoke(searchKey: String): Flow<NetworkResponse<List<Product>>> =
        repository.searchProduct(searchKey)

}