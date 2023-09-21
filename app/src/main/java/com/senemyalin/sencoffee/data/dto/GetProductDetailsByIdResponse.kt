package com.senemyalin.sencoffee.data.dto

data class GetProductDetailsByIdResponse(
    val message: String,
    val product: Product,
    val status: Int
)