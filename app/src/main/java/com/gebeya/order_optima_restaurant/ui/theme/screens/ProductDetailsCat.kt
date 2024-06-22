package com.gebeya.order_optima_restaurant.ui.theme.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomButton
import com.gebeya.order_optima_restaurant.ui.theme.componenets.RatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsCat(productId: Int,  navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val ProductsItemSaver: Saver<ProductsItem, Any> = listSaver(
        save = { listOf(it.id, it.title, it.description, it.price, it.category, it.image) },
        restore = {
            ProductsItem(
                id = it[0] as Int,
                title = it[1] as String,
                description = it[2] as String,
                price = it[3] as Double,
                category = it[4] as String,
                image = it[5] as String
            )
        }
    )
    var productsItem by rememberSaveable(stateSaver = ProductsItemSaver) { mutableStateOf(ProductsItem()) }

    LaunchedEffect(productId) {
        val result = homeViewModel.getSingleProduct(productId)
        if (result.data != null) {
             productsItem = result.data
//            homeViewModel.categoryproductList = result.data
        }
    }
    println("another print"+homeViewModel.categoryproductList)

    val context = LocalContext.current

    Scaffold(topBar = {

        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,

            ), title = {
            Row {
                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "Product Details", color = Color(0xFF021A35),
                )
            }

        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    Icons.Filled.ChevronLeft,
                    contentDescription = "Add to Cart",
                    Modifier.size(40.dp),
                    tint = Color.Black
                )
            }

        })

    },
        containerColor = Color.White,
        bottomBar = {
            Row(
                modifier = Modifier.padding(bottom = 5.dp, start = 15.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                IconButton(onClick = { }) {
                    Icon(
                        Icons.Outlined.ShoppingCart,
                        contentDescription = "delete from Cart",
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp)
                            .padding(bottom = 2.dp),
                        tint = Color(0xFF021A35)
                    )

                }
                Spacer(modifier = Modifier.width(15.dp))

                CustomButton(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(),
                    text = "Buy Now",
                    onClick = {


                    })
            }
        })

    {
        Modifier.padding(it)
        Column(
            Modifier
                .padding(horizontal = 15.dp)
        ) {
            productsItem.let { it1 ->
                productsItem.image?.let { it2 ->
                    ProductImages(it1, it2, addtofavourite = {
                        if (homeViewModel.favouriteList.any { it.productModel == productsItem }) {
                            val existingCartItem =
                                homeViewModel.favouriteList.find { it.productModel == productsItem }
                            existingCartItem?.let {
                                Toast.makeText(
                                    context,
                                    "This item have been added to favourite list already",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        } else {
                            homeViewModel.addToFavourite(
                                FavouriteModel(productModel = productsItem)

                            )
                            homeViewModel.getfavorite()

                        }
                    })
                }
            }
            Column(
                Modifier
                    .height(400.dp)
                    .verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(15.dp))

                productsItem.let { it1 ->
                    it1.title?.let { it2 ->
                        Text(
                            text = it2, color = Color.Black,
                            fontSize = 25.sp,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                productsItem.category?.let { it1 ->
                    Text(
                        text = it1, color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.width(200.dp),
                        fontWeight = FontWeight.Medium,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${productsItem.price} ETB", color = Color(0xFF346A48),
                        fontSize = 25.sp,
                        modifier = Modifier.width(200.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    var rating by remember { mutableStateOf(productsItem?.price ?: 0.0) }
                    Surface {
                        RatingBar(rating = rating) {
                            rating = it.toDouble()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Product Details", color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.width(200.dp),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))

                productsItem.description?.let { it1 ->
                    Text(
                        text = it1,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Light,
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }



        }

    }

}



