package com.senemyalin.sencoffee.domain.usecase.local.deleteallfromfavourite

import com.senemyalin.sencoffee.domain.repository.Repository
import javax.inject.Inject

class DeleteAllFromFavouriteUseCaseImpl @Inject constructor(
    private val repository: Repository
) : DeleteAllFromFavouriteUseCase {

    override suspend fun invoke() =
        repository.deleteAllProducts()
}