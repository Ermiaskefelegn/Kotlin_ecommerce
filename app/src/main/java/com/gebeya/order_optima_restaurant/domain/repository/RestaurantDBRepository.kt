package com.gebeya.order_optima_restaurant.domain.repository


import com.gebeya.order_optima_restaurant.data.db.entity.CartModel

interface RestaurantDBRepository {
    suspend fun insertAll(cart: List<CartModel>)
    suspend fun getAll(): List<CartModel>
    suspend fun insertCart(cart: CartModel)
    suspend fun deleteCart(cart: CartModel)
    suspend fun updateCart(cart: CartModel)
}