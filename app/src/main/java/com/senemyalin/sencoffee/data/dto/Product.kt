package com.senemyalin.sencoffee.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_product")
data class Product(
    val category: String,
    val count: Int,
    val description: String,
    @PrimaryKey()
    val id: Int,
    val imageOne: String,
    val imageThree: String,
    val imageTwo: String,
    val price: Double,
    val rate: Double,
    val salePrice: Double,
    val saleState: Boolean,
    val title: String
)