package com.gebeya.order_optima_restaurant.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gebeya.order_optima_restaurant.R
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.domain.repository.ResultData


data class GridItem(val title: String, val imageUrl: String)

@Composable
fun CategoriesScreen(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    var categoryList by rememberSaveable { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(Unit) {
        val result = homeViewModel.getCategory()
        if (result is ResultData.Success) {
            categoryList = result.data ?: listOf()
            println(categoryList)
        }
        homeViewModel.loading = false
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 110.dp, horizontal = 10.dp),
        color = Color.White,
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
            if (categoryList.isNotEmpty()) {
                val itemList = listOf(
                    GridItem(
                        categoryList[0],
                        "https://img.freepik.com/free-photo/modern-stationary-collection-arrangement_23-2149309643.jpg"
                    ),
                    GridItem(
                        categoryList[1],
                        "https://c8.alamy.com/comp/RM9E63/dubai-uae-dec-6-2018-gold-jewelry-in-the-display-window-of-a-jewelleries-shop-in-dubai-gold-bazaar-souk-RM9E63.jpg"

                    ),
                    GridItem(
                        categoryList[2],
                        "https://media.istockphoto.com/id/864505242/photo/mens-clothing-and-personal-accessories.jpg?s=612x612&w=0&k=20&c=TaJuW3UY9IZMijRrj1IdJRwd6iWzXBlrZyQd1uyBzEY="
                    ),
                    GridItem(
                        categoryList[3],
                        "https://media.istockphoto.com/id/1208148708/photo/polka-dot-summer-brown-dress-suede-wedge-sandals-eco-straw-tote-bag-cosmetics-on-a-light.jpg?s=612x612&w=0&k=20&c=9Y135GYKHLlPotGIfynBbMPhXNbYeuDuFzreL_nfDE8="

                    ),

                    )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        items(itemList.size) { index ->
                            GridItem(index = index,
                                title = itemList[index].title,
                                imageUrl = itemList[index].imageUrl,
                                onclick = {
                                    navController.navigate("${Screens.Categoriesdetails.route}/${itemList[index].title}")
                                })
                        }
                    })
            }

        }

    }
}

@Composable
fun GridItem(index: Int, imageUrl: String, title: String, onclick: () -> Unit) {
    // Your grid item content goes here
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onclick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(130.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),

            border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier.padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                        .crossfade(true).build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(130.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = title, color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}