package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gebeya.order_optima_restaurant.R
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel

@Composable
fun FavoriteScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    LaunchedEffect(Unit) {
        homeViewModel.getfavorite()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(color = Color(0xFFF7F7F7)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        FavoriteList()
        Spacer(modifier = Modifier.height(5.dp))

    }

}

@Composable
fun FavoriteList() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val context = LocalContext.current

    Column(
        modifier = Modifier.height(800.dp)

//            .padding(vertical = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(homeViewModel.favouriteList.size) {
            FavoriteCard(
                productName = homeViewModel.favouriteList[it].productModel.title.toString(),
                price = "$${(homeViewModel.favouriteList[it].productModel.price)}ETB",
                modifier = Modifier.padding(horizontal = 5.dp),
                imageUrl = homeViewModel.favouriteList[it].productModel.image.toString(),
                category = homeViewModel.favouriteList[it].productModel.category.toString(),
                addToCart = {

                    homeViewModel.addtoCart(
                        cartModel = CartModel(
                            quantity = 1,
                            productModel = homeViewModel.favouriteList[it].productModel
                        )
                    )
                    Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_SHORT).show()
                },
                deleteFromFavorite = {
                    homeViewModel.deleteFromFavourite(homeViewModel.favouriteList[it])
                    Toast.makeText(context, "Product Deleted From Favourite List", Toast.LENGTH_SHORT).show()

                }
            )
        }
    }
}


@Composable
fun FavoriteCard(
    productName: String,
    price: String,
    category: String,
    modifier: Modifier = Modifier,
    imageUrl: String,
    addToCart: () -> Unit,
    deleteFromFavorite: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Row(

            modifier = Modifier
                .padding(8.dp)
                .width(800.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column(

                ) {

                    Text(
                        text = productName, color = Color.Black,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = category, color = Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(1.dp))



                    Text(text = price)


                }
            }

            Column(
                modifier = Modifier.width(50.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                IconButton(onClick = { deleteFromFavorite() }) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "delete from Cart",
                        tint = Color(0xFF021A35)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                IconButton(onClick = { addToCart() }) {
                    Icon(
                        Icons.Outlined.ShoppingCart,
                        contentDescription = "Add to Cart",
                        tint = Color(0xFF021A35)
                    )
                }
            }
        }
    }
}

