package com.gebeya.order_optima_restaurant.data.network.entity

data class ProductModel(
    val id: Int,
    val name: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String,
    val discount: String?, // Change the type to the actual type of discount if applicable
    val category: Category,
    val calories: String,
    val fat: String,
    val protein: String,
    val weight: String,
    val size: String,
    val volume: String,
    val brands: String,
    val description: String
)

data class Category(
    val id: Int,
    val name: String,
    val tittle: String,
    val metaTitle: String,
    val description: String,
    val createdDate: String? // Change the type to the actual type of the date if applicable
)
