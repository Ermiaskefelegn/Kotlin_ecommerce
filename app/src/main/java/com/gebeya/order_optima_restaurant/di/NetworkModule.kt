package com.gebeya.order_optima_restaurant.di

import com.gebeya.order_optima_restaurant.data.network.api.ShoppingApi
import com.gebeya.order_optima_restaurant.domain.repository.ResultData
import com.gebeya.order_optima_restaurant.domain.repository.ResultDataDeserializer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideEvenApi(): ShoppingApi {
        val gson = GsonBuilder()
            .registerTypeAdapter(ResultData::class.java, ResultDataDeserializer<Any>()) // Adjust with the appropriate type
            .create()

        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ShoppingApi::class.java)
    }
}
