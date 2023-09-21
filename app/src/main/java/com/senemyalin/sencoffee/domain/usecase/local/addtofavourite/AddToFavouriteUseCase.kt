package com.senemyalin.sencoffee.domain.usecase.local.addtofavourite

import com.senemyalin.sencoffee.data.dto.Product

interface AddToFavouriteUseCase {
    suspend operator fun invoke(product: Product)
}