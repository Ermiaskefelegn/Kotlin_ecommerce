package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gebeya.order_optima_restaurant.R
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel
import com.gebeya.order_optima_restaurant.data.db.entity.CartModel
import com.gebeya.order_optima_restaurant.data.network.model.OrderItem
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomButton

@Composable
fun CartScreen(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(Unit) {
        homeViewModel.getCart()
    }
    val formattedValue = String.format("%.2f", homeViewModel.totalPrice.value)

    Scaffold(
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 5.dp)
                .background(color = Color.White),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CartList(homeViewModel.cartList)
            Spacer(modifier = Modifier.height(5.dp))
            PriceCard(title = "Total Items", value = "${homeViewModel.cartList.size}")

            PriceCard(title = "Service Charge", value = "7%")
            PriceCard(title = "Total Price", value = "${formattedValue}ETB")
            Spacer(modifier = Modifier.height(5.dp))

            CustomButton(modifier = Modifier
                .width(400.dp)
                .height(60.dp)
                .padding(bottom = 0.dp, start = 30.dp, end = 30.dp),
                text = "Checkout",
                onClick = {
                    homeViewModel.showDialog = true

                })
            Spacer(modifier = Modifier.height(70.dp))

            if (homeViewModel.showDialog) PopupModal(navController)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupModal(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    val formattedValue = String.format("%.2f", homeViewModel.totalPrice.value)
    val showDialog = remember { mutableStateOf(false) }
    val loadingState = remember { mutableStateOf(false) }
    val orderList = remember { mutableStateListOf<OrderItem>() }

    Dialog(onDismissRequest = { homeViewModel.showDialog = false }) {
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(600.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Checkout"
                        )
                    },
                    Modifier.background(color = Color.White),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OrderList(
                    cartlist = homeViewModel.cartList
                )
                PriceCard(title = "Total Items", value = "${homeViewModel.cartList.size}")

                PriceCard(title = "Service Charge", value = "7%")
                PriceCard(title = "Total Price", value = "${formattedValue}ETB")
                CustomButton(modifier = Modifier
                    .width(400.dp)
                    .height(60.dp)

                    .padding(bottom = 0.dp, start = 30.dp, end = 30.dp),
                    text = "Pay with MPESA",
                    onClick = {
                        homeViewModel.cartList.forEach { orderItem ->
                            orderList.add(
                                OrderItem(
                                    productId = orderItem.productModel.id,
                                    quantity = orderItem.quantity
                                )
                            )
                        }
//
                        loadingState.value = true
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingState.value = false
                            showDialog.value = true
                        }, 3000)

                    })
            }
            if (loadingState.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            if (showDialog.value) {
                AlertDialog(onDismissRequest = { showDialog.value = false },
                    title = { Text(text = "Success!") },
                    text = { Text(text = "Your order has been submitted successfully.") },
                    confirmButton = {
                        Button(
                            onClick = { showDialog.value = false },
                        ) {
                            Text("OK")
                        }
                    })
            }
        }
    }
}

@Composable
fun CartList(cartlist: List<CartModel>) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    Column(
        modifier = Modifier
            .height(440.dp)
//            .padding(vertical = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(cartlist.size) {
            cartlist[it].productModel.title?.let { it1 ->
                cartlist[it].productModel.image?.let { it2 ->
                    cartlist[it].productModel.category?.let { it3 ->
                        CartCard(productName = it1,
                            price = "${cartlist[it].productModel.price}ETB",
                            modifier = Modifier.padding(horizontal = 5.dp),
                            imageUrl = it2,
                            quantity = cartlist[it].quantity ?: 1,
                            category = it3,
                            deleteItem = {
                                homeViewModel.deleteFromCart(cartlist[it])
                            },
                            decreaseQuantity = {

                                val newQuantity = cartlist[it].quantity?.minus(1)
                                if (newQuantity != null && newQuantity != 0) {
                                    val newCartValue = cartlist[it].copy(quantity = newQuantity)
                                    homeViewModel.updateCart(newCartValue)
                                    homeViewModel.calculateTotalPrice()
                                } else if (newQuantity == 0) {
                                    homeViewModel.deleteFromCart(cartlist[it])
                                    homeViewModel.calculateTotalPrice()
                                }
                            },
                            increaseQuantity = {
                                val newQuantity = cartlist[it].quantity?.plus(1)
                                if (newQuantity != null) {
                                    val newCartValue = cartlist[it].copy(quantity = newQuantity)
                                    homeViewModel.updateCart(newCartValue)
                                    homeViewModel.calculateTotalPrice()
                                }
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun OrderList(cartlist: List<CartModel>) {

    Column(
        modifier = Modifier
            .height(350.dp)
//            .padding(vertical = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        repeat(cartlist.size) {
            cartlist[it].productModel.title?.let { it1 ->
                cartlist[it].productModel.image?.let { it2 ->
                    cartlist[it].productModel.category?.let { it3 ->
                        OrderCard(
                            productName = it1,
                            price = "${cartlist[it].productModel.price}ETB",
                            modifier = Modifier.padding(horizontal = 5.dp),
                            imageUrl = it2,
                            quantity = cartlist[it].quantity ?: 1,
                            category = it3
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PriceCard(title: String, value: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = value, color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun CartCard(
    productName: String,
    price: String,
    category: String,
    quantity: Int,
    modifier: Modifier = Modifier,
    imageUrl: String,
    deleteItem: () -> Unit,
    increaseQuantity: () -> Unit,
    decreaseQuantity: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Row(

            modifier = Modifier
                .padding(vertical = 5.dp)
                .width(800.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                        .crossfade(true).build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                )
                Spacer(modifier = Modifier.width(0.dp))

                Column(

                ) {
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        modifier = Modifier.width(150.dp),
                        text = productName, color = Color.Black,
                        fontSize = 16.sp,
                        maxLines = 1,
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
                        modifier = Modifier.width(100.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .size(25.dp)
                                .padding(1.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,

                                ),
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(4.dp),


                            ) {
                            IconButton(onClick = { increaseQuantity() }) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Add to Cart",
                                    tint = Color(0xFF021A35)
                                )
                            }
                        }
                        Text(text = "$quantity")
                        Card(
                            modifier = Modifier
                                .size(25.dp)
                                .padding(1.dp)
                                .clip(RoundedCornerShape(2)),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,

                                ),
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(4.dp),


                            ) {
                            IconButton({ decreaseQuantity() }) {
                                Icon(
                                    Icons.Filled.Remove,
                                    contentDescription = "Add to Cart",
                                    tint = Color(0xFF021A35)
                                )
                            }
                        }

                    }
                }
            }

            Column(
                modifier = Modifier.width(100.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                IconButton(onClick = { deleteItem() }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "delete from Cart",
                        tint = Color(0xFF021A35)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                Text(text = price)
            }
        }
    }
}

@Composable
fun OrderCard(
    productName: String,
    price: String,
    category: String,
    quantity: Int,
    modifier: Modifier = Modifier,
    imageUrl: String,

    ) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),

        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Row(

            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 2.dp)
                .width(800.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                        .crossfade(true).build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                )
                Column(

                ) {
                    Spacer(modifier = Modifier.height(5.dp))

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

                    Row {
                        Text(
                            text = "$quantity Qty", color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                        Spacer(modifier = Modifier.width(150.dp))
                        Text(
                            text = "$quantity Qty", color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
            Text(text = price)
        }
    }
}

