package com.gebeya.order_optima_restaurant.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import java.util.UUID


@Entity(
    tableName = "cart"
)
data class CartModel (
    @PrimaryKey val cartId: UUID = UUID.randomUUID(),
    val quantity: Int? = null,
    val productModel: ProductsItem
)
