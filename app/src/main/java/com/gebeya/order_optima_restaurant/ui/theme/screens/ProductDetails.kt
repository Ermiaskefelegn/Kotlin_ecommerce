package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gebeya.order_optima_restaurant.R
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.FavouriteModel
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomButton
import com.gebeya.order_optima_restaurant.ui.theme.componenets.RatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(productsItem: ProductsItem, navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
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
            ProductImages(productsItem, productsItem.image.toString(), addtofavourite = {
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
            Column(Modifier.height(400.dp)
                .verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = productsItem.title.toString(), color = Color.Black,
                    fontSize = 25.sp,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = productsItem.category.toString(), color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.width(200.dp),
                    fontWeight = FontWeight.Medium,
                )
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
                    var rating by remember { mutableStateOf(productsItem.price) }
                    Surface {
                        rating?.let { it1 ->
                            RatingBar(rating = it1.toDouble()) {
                                rating = it.toDouble()
                            }
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

                Text(
                    text = productsItem.description.toString(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.height(15.dp))
            }



        }

    }

}


@Composable
fun ProductImages(productsItem: ProductsItem, imageUrl: String, addtofavourite: () -> Unit) {
    val selectedIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    val homeViewModel = hiltViewModel<HomeViewModel>()


    Column {

        Row(
            modifier = Modifier
                .padding(top = 65.dp, start = 5.dp, end = 5.dp)
                .horizontalScroll(scrollState)
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .width(380.dp)
                        .height(215.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .width(330.dp)
                            .height(200.dp)
                            .padding(horizontal = 5.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(Color.White),
                        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
                    ) {


                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                                .crossfade(true).build(),
                            placeholder = painterResource(R.drawable.image),
                            contentDescription = "Product Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .width(300.dp)
                                .height(180.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { addtofavourite() }) {
                            if (homeViewModel.favouriteList.any { it.productModel == productsItem }) {
                                val existingCartItem =
                                    homeViewModel.favouriteList.find { it.productModel == productsItem }
                                existingCartItem?.let {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = "Add to Cart",
                                        Modifier.padding(10.dp),
                                        tint = Color.Black
                                    )
                                }
                            } else {
                                Icon(
                                    Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Add to Cart",
                                    Modifier.padding(10.dp),
                                    tint = Color.Black
                                )
                            }

                        }

                    }
                }
            }

        }
        IndicatorRow(
            itemCount = 3, selectedIndex = selectedIndex, modifier = Modifier
        )
    }


}


@Composable
fun IndicatorRow(
    itemCount: Int, selectedIndex: Int, modifier: Modifier = Modifier
) {

    Canvas(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        drawIntoCanvas { canvas ->
            val indicatorRadius = 6.dp.toPx()
            val indicatorSpacing = 8.dp.toPx()

            val totalWidth =
                (itemCount * indicatorRadius * 2) + ((itemCount - 1) * indicatorSpacing)
            val startX = (size.width - totalWidth) / 2

            for (i in 0 until itemCount) {
                val x = startX + (i * (indicatorRadius * 2 + indicatorSpacing))
                val radius = indicatorRadius.toFloat()
                val centerY = size.height / 2
                val paint = Paint().apply {
                    color = if (i == selectedIndex) Color(0xFF021A35) else Color.Gray
                }
                canvas.drawCircle(
                    center = Offset(x + radius, centerY),
                    radius = radius,
                    paint = paint,
                )
            }
        }
    }

}