package com.senemyalin.sencoffee.data.dto

data class GetAllProductsResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)