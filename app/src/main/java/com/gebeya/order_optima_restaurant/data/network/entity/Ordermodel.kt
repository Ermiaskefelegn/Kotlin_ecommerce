package com.gebeya.order_optima_restaurant.data.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


data class OrderModel (
    val restaurantId: Int? = null,
    val orderItems: List<OrderItem>? = null
)

data class OrderItem (
    val productId: Int? = null,
    val quantity: Int? = null
)
