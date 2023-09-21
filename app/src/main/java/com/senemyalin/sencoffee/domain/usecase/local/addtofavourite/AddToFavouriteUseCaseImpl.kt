package com.senemyalin.sencoffee.domain.usecase.local.addtofavourite

import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import javax.inject.Inject

class AddToFavouriteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : AddToFavouriteUseCase {

    override suspend fun invoke(product: Product) =
        repository.addProduct(product)
}