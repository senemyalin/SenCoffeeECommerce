package com.senemyalin.sencoffee.data.dto

data class GetSaleProductsResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)