package com.gebeya.order_optima_restaurant.data.network.model


data class OrderModel (
    val restaurantId: Int? = null,
    val orderItems: List<OrderItem>? = null
)

data class OrderItem (
    val productId: Int? = null,
    val quantity: Int? = null
)
