package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetails(
    navController: NavController, categoryId: String
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    var categoryproductList by rememberSaveable { mutableStateOf(emptyList<ProductsItem>()) }

    LaunchedEffect(categoryId) {
        val result = homeViewModel.getProductByCategory(categoryId)
        if (result.data != null) {
            categoryproductList = result.data
            homeViewModel.categoryproductList = result.data
        }
    }
    println(homeViewModel.categoryproductList)
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {

            CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,

                ), title = {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = categoryId.uppercase(), color = Color(0xFF021A35),
                    )
                }

            }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.Filled.ChevronLeft,
                        contentDescription = "back",
                        Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }

            })

        },
        containerColor = Color.White,
    ) {
        val getSize: () -> Int = { categoryproductList.size }

        Column(
            modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (homeViewModel.loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(color = Color(0xFF021A35))
                }
            } else {

                ProductGrid(

                    products = categoryproductList,

                    navController = navController, homeViewModel = homeViewModel
                )
            }
        }

    }
}

@Composable
fun ProductGrid(
    products: List<ProductsItem>,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        content = {
            items(products.size) { index ->
                val product = products[index]
                ProductCard(
                    productName = product.title.toString(),
                    price = "${product.price} ETB",
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                        .clickable(onClick = {
                            val productId = product.id // Use the product's actual ID
                            navController.navigate("${Screens.ProductScreen2.route}/$productId")
                        }),
                    imageUrl = product.image.toString(),
                    category = product.category.toString(),
                    addToCart = {
                        homeViewModel.addtoCart(
                            cartModel = CartModel(
                                quantity = 1,
                                productModel = product
                            )
                        )
                        Toast.makeText(
                            context, "Product Added to Cart Successfully", Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    )
}
