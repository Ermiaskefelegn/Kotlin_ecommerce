package com.gebeya.order_optima_restaurant.data.network.repository


import com.gebeya.order_optima_restaurant.data.network.api.LoginRequest
import com.gebeya.order_optima_restaurant.data.network.api.ShoppingApi
import com.gebeya.order_optima_restaurant.data.network.model.AuthenticationToken
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.data.network.model.UserModel
import com.gebeya.order_optima_restaurant.domain.repository.APIRepositoryDef
import com.gebeya.order_optima_restaurant.domain.repository.PreferencesRepository
import com.gebeya.order_optima_restaurant.domain.repository.ResultData
import retrofit2.HttpException
import java.io.IOException

class APIRepositoryImpl(
    private var shoppingApi: ShoppingApi,
    private var preferencesRepository: PreferencesRepository
) : APIRepositoryDef {

    override suspend fun getUser(userId:Int): ResultData<UserModel> {
        try {
            val user = shoppingApi.getUser(userId)

            return ResultData.Success(user)
        } catch (e: IOException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            println("network error: ${t.message}")
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }
    override suspend fun getProduct(): ResultData<List<ProductsItem>> {
        try {
            val products = shoppingApi.getProduct()

            return ResultData.Success(products)
        } catch (e: IOException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            println("network error: ${t.message}")
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }

    override suspend fun getSingleProduct(productId:Int): ResultData<ProductsItem> {
        try {
            val products = shoppingApi.getSingleProduct(productId)

            return ResultData.Success(products)
        } catch (e: IOException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            println("network error: ${t.message}")
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }

    override suspend fun getCategories(): ResultData<List<String>> {
        try {
            val categories = shoppingApi.getcategory()
println(categories)
            return ResultData.Success(categories)
        } catch (e: IOException) {
            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            println("HttpException: ${e.response()?.errorBody()?.string()}")

            println("network error: ${e.message}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            println("network error: ${t.message}")
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }


    override suspend fun registerUser(userModel: UserModel): ResultData<UserModel> {

        try {
            val result = shoppingApi.registerUser(userModel = userModel)
            println("network data: $userModel")
            println("network data: $result")
            return ResultData.Success(data = result)
        } catch (e: IOException) {
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }

    override suspend fun getProductbyCategoryId(category: String): ResultData<List<ProductsItem>> {
        try {
            val result = shoppingApi.getProductByCategoryId(category = category)
            println("network data: $result")
            return ResultData.Success(data = result)
        } catch (e: IOException) {
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (e: HttpException) {
            println("HttpException: ${e.response()?.errorBody()?.string()}")
            return ResultData.Fail(errorMessage = e.message ?: "")
        } catch (t: Throwable) {
            return ResultData.Fail(errorMessage = t.message ?: "")
        }
    }

    override suspend fun login(
        username: String,
        password: String
    ): ResultData<AuthenticationToken> {
        try {
            val loginRequest = LoginRequest(username = username, password = password)
            println(loginRequest)
            val authenticationToken = shoppingApi.login(loginRequest)
            preferencesRepository.saveAuthenticationToken(authenticationToken)
            return ResultData.Success(data = authenticationToken)
        } catch (e: IOException) {
            return ResultData.Fail(errorMessage = e.message ?: "no network")
        } catch (e: HttpException) {
            println("HttpException: ${e.response()?.errorBody()?.string()}")
            return ResultData.Fail(errorMessage = e.message ?: "server error")
        } catch (e: Throwable) {
            return ResultData.Fail(errorMessage = e.message ?: "unknown error")
        }
    }

}
