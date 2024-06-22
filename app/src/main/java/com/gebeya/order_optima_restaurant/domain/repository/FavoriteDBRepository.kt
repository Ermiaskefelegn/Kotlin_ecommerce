package com.gebeya.order_optima_restaurant.domain.repository


import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.data.network.entity.ProductModel

interface FavoriteDBRepository {
    suspend fun insert(favouriteModel: FavouriteModel)
    suspend fun getAll(): List<FavouriteModel>
    suspend fun deleteCart(favouriteModel: FavouriteModel)
}
