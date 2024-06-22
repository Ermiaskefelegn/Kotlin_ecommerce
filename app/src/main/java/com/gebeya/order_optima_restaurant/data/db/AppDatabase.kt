package com.gebeya.order_optima_restaurant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gebeya.order_optima_restaurant.data.db.dao.CartDao
import com.gebeya.order_optima_restaurant.data.db.dao.FavoriteDao
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel

@TypeConverters(TypeConverterRoom::class)
@Database(entities = [ CartModel::class , FavouriteModel::class ], version = 3,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao

    abstract fun favouriteDAO(): FavoriteDao
}