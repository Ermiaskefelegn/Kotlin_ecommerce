package com.gebeya.order_optima_restaurant.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
@Dao
interface CartDao {

    @Insert
    suspend fun insert(cart: CartModel)

    @Insert
    suspend fun insertall(carts: List<CartModel>)

    @Query("select * from cart")
    suspend fun getAll(): List<CartModel>

    @Update
    suspend fun update(carts: CartModel)


    @Delete
    suspend fun deletecart(carts: CartModel)





}