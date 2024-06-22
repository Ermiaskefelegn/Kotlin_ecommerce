package com.gebeya.order_optima_restaurant.data.network.model

data class ProductsItem(
    val category: String?=null,
    val description: String?=null,
    val id: Int?=null,
    val image: String?=null,
    val price: Double?=null,
    val rating: Rating?=null,
    val title: String?=null
)