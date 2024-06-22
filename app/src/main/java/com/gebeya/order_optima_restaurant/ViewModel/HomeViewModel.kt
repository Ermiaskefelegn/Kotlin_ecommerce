package com.gebeya.order_optima_restaurant.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.domain.repository.APIRepositoryDef
import com.gebeya.order_optima_restaurant.domain.repository.FavoriteDBRepository
import com.gebeya.order_optima_restaurant.domain.repository.PreferencesRepository
import com.gebeya.order_optima_restaurant.domain.repository.RestaurantDBRepository
import com.gebeya.order_optima_restaurant.domain.repository.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomeViewModel @Inject constructor(
    private val APIRepositoryDef: APIRepositoryDef,
    private val restaurantDBRepository: RestaurantDBRepository,
    private val favoriteDBRepository: FavoriteDBRepository,
    private val preferencesRepository: PreferencesRepository,

    ) : ViewModel() {

    var productList: List<ProductsItem> by mutableStateOf(listOf())
    var categoryproductList: List<ProductsItem> by mutableStateOf(listOf())
    var loading: Boolean by mutableStateOf(false)
//    var selectedcategory: String by mutableStateOf("")

    var cartList: List<CartModel> by mutableStateOf(listOf())
    var favouriteList: List<FavouriteModel> by mutableStateOf(listOf())
    var showDialog by  mutableStateOf(false)

    val totalPrice: MutableState<Double> = mutableStateOf(0.0)
    init {
        getProduct()
        calculateTotalPrice()
        getfavorite()

    }

    private fun getProduct() {
        loading=true
        viewModelScope.launch {
            when (val result = APIRepositoryDef.getProduct()) {
                is ResultData.Fail -> println("Network issue: ${result.errorMessage}")
                is ResultData.Success -> productList = result.data ?: listOf()
            }

        }
loading=false
    }
    suspend fun getSingleProduct(productID: Int): ResultData<ProductsItem> {
        loading = true
        return when (val result = APIRepositoryDef.getSingleProduct(productID)) {
            is ResultData.Fail -> {
                println("Network issue: ${result.errorMessage}")
                loading = false
                result
            }
            is ResultData.Success -> {
                loading = false
                result
            }
        }
    }

    suspend fun getCategory(): ResultData<List<String>> {
        loading = true
        val result = APIRepositoryDef.getCategories()
        when (result) {
            is ResultData.Fail -> println("Network issue: ${result.errorMessage}")
            is ResultData.Success -> {
//                categorlist = result.data ?: listOf()

            }
        }
        loading = false
        return result
    }

    suspend fun getProductByCategory(categoryId: String): ResultData<List<ProductsItem>> {
        loading=true
        return when (val result = APIRepositoryDef.getProductbyCategoryId(categoryId)) {
            is ResultData.Fail -> {
                loading=false

                result
            }
            is ResultData.Success -> {
//                categoryProductList = result.data ?: listOf()
                loading=false

                result

            }

        }
    }


    fun calculateTotalPrice() {
        var total = 0.0
        cartList.forEach { cartItem ->
            val quantity = cartItem.quantity ?: 1
            val price = cartItem.productModel.price
            total += quantity * price!!
        }
        totalPrice.value = total + (total*7/100)
        println(totalPrice.value)

    }
     fun getCart() {
        viewModelScope.launch {
            val result = restaurantDBRepository.getAll()
            if (result.isNotEmpty()) {
                cartList = result
                calculateTotalPrice()
            } else {
                cartList = listOf()
            }
        }
    }

    fun addtoCart(cartModel: CartModel) {
        viewModelScope.launch {
            if (cartList.any { it.productModel == cartModel.productModel }) {
                val existingCartItem = cartList.find { it.productModel == cartModel.productModel }
                existingCartItem?.let {
                    val newQuantity = (it.quantity ?: 0) + 1
                    val newCartValue = it.copy(quantity = newQuantity)
                    restaurantDBRepository.updateCart(newCartValue)
                    calculateTotalPrice()
                }
            } else{
                restaurantDBRepository.insertCart(cartModel)
                getCart()
            }

        }
    }

    fun updateCart(cartModel: CartModel) {
        viewModelScope.launch {
            restaurantDBRepository.updateCart(cartModel)
            getCart()
        }
    }

    fun addToFavourite(favouriteModel: FavouriteModel) {
        viewModelScope.launch {

            favoriteDBRepository.insert(favouriteModel)
            getfavorite()
        }
    }
     fun getfavorite() {
        viewModelScope.launch {
            val result = favoriteDBRepository.getAll()
            if (result.isNotEmpty()) {
                favouriteList = result
            } else {
                favouriteList = listOf()
            }
        }
    }
    fun deleteFromCart(cartModel: CartModel) {
        viewModelScope.launch {
            restaurantDBRepository.deleteCart(cartModel)
            getCart()
        }
    }

    fun deleteFromFavourite(favouriteModel: FavouriteModel) {
        viewModelScope.launch {
            favoriteDBRepository.deleteCart(favouriteModel)
            getfavorite()
        }
    }





}