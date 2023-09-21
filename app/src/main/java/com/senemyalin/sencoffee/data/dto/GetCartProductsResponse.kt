package com.senemyalin.sencoffee.data.dto

data class GetCartProductsResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)