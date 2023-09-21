package com.senemyalin.sencoffee.data.dto

data class AddToCartRequest(
    val productId: Int,
    val userId: String
)