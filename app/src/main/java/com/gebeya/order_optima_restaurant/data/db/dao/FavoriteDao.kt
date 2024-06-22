package com.gebeya.order_optima_restaurant.data.db.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.data.network.entity.ProductModel

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insert(favouriteModel: FavouriteModel)



    @Query("select * from favorite")
    suspend fun getAll(): List<FavouriteModel>




    @Delete
    suspend fun deletecart(favouriteModel: FavouriteModel)





}

