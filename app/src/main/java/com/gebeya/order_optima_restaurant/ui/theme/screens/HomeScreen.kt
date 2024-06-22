package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gebeya.order_optima_restaurant.R
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem

@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val loading by remember { derivedStateOf { homeViewModel.loading } }
println("loading"+loading)
    Scaffold (
        containerColor = Color.White,
    ){
        if(loading){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator(color =Color(0xFF021A35))
            }
        }
        else{
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
                .padding(top = 65.dp)
        ) {

            BannerCard()

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "New Products", color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            ProductList(navController = navController, homeViewModel.productList)
            Text(
                text = "Recommended Products", color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            ProductList(navController = navController, homeViewModel.productList)}
        }
    }
}


@Composable
fun ProductList(navController: NavController, productlist: List<ProductsItem>) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(productlist.size) {
            ProductCard(productName = productlist[it].title.toString(),
                price = "${productlist[it].price} ETB",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable(onClick = {
                        val productId = it
                        navController.navigate("${Screens.ProductScreen.route}/$productId")
                    }),
                imageUrl = productlist[it].image.toString(),
                category = productlist[it].category.toString(),
                addToCart = {
                    homeViewModel.addtoCart(
                        cartModel = CartModel(
                            quantity = 1, productModel = productlist[it]
                        )
                    )
                    Toast.makeText(
                        context, "Product Added to Cart Successfully", Toast.LENGTH_SHORT
                    ).show()

                })
        }
    }
}

@Composable
fun BannerCard() {
    Row(
        modifier = Modifier

            .horizontalScroll(rememberScrollState())
    ) {

            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(140.dp)
                    .padding(horizontal = 5.dp),
                shape = RoundedCornerShape(8.dp),
//        backgroundColor = Color.White,
                border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
            ) {

                Image(
                    painter = painterResource(R.drawable.banner1),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(300.dp)
                        .height(150.dp)
                )


        }
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(140.dp)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(8.dp),
//        backgroundColor = Color.White,
            border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
        ) {

            Image(
                painter = painterResource(R.drawable.banner2),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(300.dp)
                    .height(150.dp)
            )


        }
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(140.dp)
                .padding(horizontal = 5.dp),
            shape = RoundedCornerShape(8.dp),
//        backgroundColor = Color.White,
            border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
        ) {

            Image(
                painter = painterResource(R.drawable.banner3),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(300.dp)
                    .height(150.dp)
            )


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    productName: String,
    category: String,
    price: String,
    modifier: Modifier = Modifier,
    imageUrl: String,
    addToCart: () -> Unit
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.Start
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.image),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = productName, color = Color.Black,
                maxLines = 1,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = category, color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.width(150.dp), horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(text = price, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Card(
                    modifier = Modifier
                        .size(25.dp)
                        .padding(2.dp)

//                        .background(color = Color(0xFF021A35))
                        .clip(CircleShape),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF021A35),

                        ),
                    shape = CircleShape,


                    ) {
                    IconButton(onClick = { addToCart() }) {
                        Icon(
                            Icons.Filled.Add, contentDescription = "Add to Cart", tint = Color.White
                        )
                    }
                }

            }


        }
    }
}

