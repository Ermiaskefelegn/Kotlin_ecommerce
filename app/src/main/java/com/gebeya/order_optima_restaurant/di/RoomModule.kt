package com.gebeya.order_optima_restaurant.di

import android.app.Application
import androidx.room.Room
import com.gebeya.order_optima_restaurant.data.db.AppDatabase
import com.gebeya.order_optima_restaurant.data.db.dao.CartDao
import com.gebeya.order_optima_restaurant.data.db.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java,
            name = "cart_db"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(appDatabase: AppDatabase): CartDao{
        return appDatabase.cartDao()
    }
    @Provides
    @Singleton
    fun provideFavouriteDao(appDatabase: AppDatabase): FavoriteDao{
        return appDatabase.favouriteDAO()
    }


}