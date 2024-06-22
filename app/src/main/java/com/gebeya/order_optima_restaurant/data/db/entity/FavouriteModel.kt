package com.gebeya.order_optima_restaurant.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import java.util.UUID


@Entity(
    tableName = "favorite"
)
data class FavouriteModel (
    @PrimaryKey val favouriteId: UUID = UUID.randomUUID(),
    val productModel: ProductsItem
)


