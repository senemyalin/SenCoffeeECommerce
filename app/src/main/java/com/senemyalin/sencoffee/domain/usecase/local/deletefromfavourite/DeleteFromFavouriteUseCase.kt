package com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite

import com.senemyalin.sencoffee.data.dto.Product

interface DeleteFromFavouriteUseCase {
    suspend operator fun invoke(product: Product)
}