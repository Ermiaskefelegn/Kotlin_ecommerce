package com.gebeya.order_optima_restaurant.data.db.repository

import com.gebeya.order_optima_restaurant.data.db.dao.CartDao
import com.gebeya.order_optima_restaurant.data.db.dao.FavoriteDao
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.domain.repository.FavoriteDBRepository
import com.gebeya.order_optima_restaurant.domain.repository.RestaurantDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class FavouriteDBRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteDBRepository {
    override suspend fun insert(favouriteModel: FavouriteModel) {
        return favoriteDao.insert(favouriteModel )
    }

    override suspend fun getAll(): List<FavouriteModel> {
        return favoriteDao.getAll()

    }



    override suspend fun deleteCart(favouriteModel: FavouriteModel) {
        return favoriteDao.deletecart(favouriteModel)
    }




}
