package com.gebeya.order_optima_restaurant.data.db.repository

import com.gebeya.order_optima_restaurant.data.db.dao.CartDao
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.domain.repository.RestaurantDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class CartDBRepositoryimpl(
    private val companyDatabaseDao: CartDao
) : RestaurantDBRepository {
    override suspend fun insertAll(carts: List<CartModel>) {
        return companyDatabaseDao.insertall(carts)
    }

    override suspend fun getAll(): List<CartModel> {
        return companyDatabaseDao.getAll()

    }

    override suspend fun insertCart(cart: CartModel) {
        return companyDatabaseDao.insert(cart)
    }

    override suspend fun deleteCart(cart: CartModel) {
        return companyDatabaseDao.deletecart(cart)
    }

    override suspend fun updateCart(cart: CartModel) {
        return companyDatabaseDao.update(cart)
    }


}