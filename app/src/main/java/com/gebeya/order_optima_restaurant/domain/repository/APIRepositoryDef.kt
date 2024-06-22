package com.gebeya.order_optima_restaurant.domain.repository

import com.gebeya.order_optima_restaurant.data.network.entity.AuthenticationToken
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.data.network.model.UserModel

interface APIRepositoryDef {
    suspend fun getUser(userId:Int): ResultData<UserModel>

    suspend fun getCategories(): ResultData<List<String>>

    suspend fun getProduct(): ResultData<List<ProductsItem>>

    suspend fun getSingleProduct(productId: Int): ResultData<ProductsItem>


    suspend fun registerUser(userModel: UserModel): ResultData<UserModel>

    suspend fun getProductbyCategoryId(category: String): ResultData<List<ProductsItem>>

    suspend fun login(username:String,password:String): ResultData<AuthenticationToken>
}