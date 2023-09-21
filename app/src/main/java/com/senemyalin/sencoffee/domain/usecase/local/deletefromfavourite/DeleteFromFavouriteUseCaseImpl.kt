package com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite

import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.repository.Repository
import com.senemyalin.sencoffee.domain.usecase.local.deleteallfromfavourite.DeleteAllFromFavouriteUseCase
import javax.inject.Inject

class DeleteFromFavouriteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : DeleteFromFavouriteUseCase {

    override suspend fun invoke(product: Product) =
        repository.deleteProduct(product)
}