package com.senemyalin.sencoffee.data.dto

data class SearchProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)