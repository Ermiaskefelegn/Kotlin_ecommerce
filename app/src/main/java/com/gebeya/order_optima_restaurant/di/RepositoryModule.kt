package com.gebeya.order_optima_restaurant.di
import android.app.Application
import com.gebeya.eventnotifier.data.SharedPreferences.PreferencesRepositoryImpl
import com.gebeya.order_optima_restaurant.data.db.dao.CartDao
import com.gebeya.order_optima_restaurant.data.db.dao.FavoriteDao
import com.gebeya.order_optima_restaurant.data.db.repository.CartDBRepositoryimpl
import com.gebeya.order_optima_restaurant.data.db.repository.FavouriteDBRepositoryImpl
import com.gebeya.order_optima_restaurant.data.network.api.ShoppingApi
import com.gebeya.order_optima_restaurant.data.network.repository.APIRepositoryImpl
import com.gebeya.order_optima_restaurant.domain.repository.FavoriteDBRepository
import com.gebeya.order_optima_restaurant.domain.repository.PreferencesRepository
import com.gebeya.order_optima_restaurant.domain.repository.RestaurantDBRepository
import com.gebeya.order_optima_restaurant.domain.repository.APIRepositoryDef
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventRepository(shoppingApi: ShoppingApi, preferencesRepository: PreferencesRepository): APIRepositoryDef {
        return APIRepositoryImpl(shoppingApi,preferencesRepository)
    }

    @Provides
    @Singleton
    fun providesCartDBrepository(cartDao: CartDao): RestaurantDBRepository{
        return CartDBRepositoryimpl(cartDao)
    }

    @Provides
    @Singleton
    fun providesFavouriteDBRepository(favoriteDao: FavoriteDao): FavoriteDBRepository{
        return FavouriteDBRepositoryImpl(favoriteDao )
    }
//
    @Provides
    @Singleton
    fun providePreferencesRepository(application: Application): PreferencesRepository{
        return PreferencesRepositoryImpl(application)
    }

}