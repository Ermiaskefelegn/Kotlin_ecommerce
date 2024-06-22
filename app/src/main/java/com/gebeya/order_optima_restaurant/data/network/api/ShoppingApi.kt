package com.gebeya.order_optima_restaurant.data.network.api

import com.gebeya.order_optima_restaurant.data.network.entity.AuthenticationToken
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.data.network.model.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val AUTHORIZATION = "Authorization"
data class LoginRequest(
    val username: String,
    val password: String
)
interface ShoppingApi {
    @GET("/products")
    suspend fun getProduct(): List<ProductsItem>

    @GET("products/categories")
    suspend fun getcategory(): List<String>




    @POST("/users")
    suspend fun registerUser(
        @Body userModel: UserModel
    ): UserModel

    @GET("/products/category/{category}")
    suspend fun getProductByCategoryId(
        @Path("category") category: String
    ): List<ProductsItem>

    @GET("/products/{id}")
    suspend fun getSingleProduct(
        @Path("id") productId: Int
    ): ProductsItem

    @GET("/users/{id}")
    suspend fun getUser(
        @Path("id") userID: Int
    ): UserModel


    @POST("/auth/login")
    suspend fun login(
       @Body request: LoginRequest
    ): AuthenticationToken


}