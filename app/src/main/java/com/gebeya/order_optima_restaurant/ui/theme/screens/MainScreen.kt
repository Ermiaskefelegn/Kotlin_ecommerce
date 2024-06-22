package com.gebeya.order_optima_restaurant.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    val selectedIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    ),
                title = {
                Text(
                    when (selectedIndex.value) {
                        1 -> "Categories"
                        2 -> "Cart"
                        3 -> "Favorites"
                        4 -> "Profile"
                        else -> "Home"
                    }, color = Color.Black
                )
            })
        },
        containerColor = Color.White,
        bottomBar = {
            BottomNavigationBar() { newIndex ->
                selectedIndex.value = newIndex
            }
        },

        ) {
        Modifier.padding(it).padding(top = 20.dp)
        if (selectedIndex.value == 0) {
            HomeScreen(navController = navController)
        } else if (selectedIndex.value == 1) {
            CategoriesScreen(navController = navController)
        } else if (selectedIndex.value == 2) {
            CartScreen(navController)
        } else if (selectedIndex.value == 3) {
            FavoriteScreen()
        } else if (selectedIndex.value == 4) {
            ProfileScreen(navController = navController)
        }

    }

}


@Composable
fun BottomNavigationBar(
//    selectedIndexPage: Int?,
    onSelectedIndexChanged: (Int) -> Unit

) {
    val selectedIndex = remember { mutableStateOf(0) }
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        containerColor = Color.White,
        tonalElevation = 2.dp
    ) {


        NavigationBarItem(icon = {
            Icon(
                imageVector = if (selectedIndex.value == 0) Icons.Filled.Home else Icons.Outlined.Home,
                ""
            )
        }, label = { Text(text = "Home") }, selected = (selectedIndex.value == 0), onClick = {
            selectedIndex.value = 0
            onSelectedIndexChanged(selectedIndex.value)
        })

        NavigationBarItem(icon = {
            Icon(
                imageVector = if (selectedIndex.value == 1) Icons.Default.Menu else Icons.Outlined.Menu,
                ""
            )
        },
            label = { Text(text = "Categories") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                onSelectedIndexChanged(selectedIndex.value)

            })

        NavigationBarItem(icon = {
            Icon(
                imageVector = if (selectedIndex.value == 2) Icons.Default.ShoppingCart else Icons.Outlined.ShoppingCart,
                ""
            )
        }, label = { Text(text = "Cart") }, selected = (selectedIndex.value == 2), onClick = {
            selectedIndex.value = 2
            onSelectedIndexChanged(selectedIndex.value)

        })

        NavigationBarItem(icon = {
            Icon(
                imageVector = if (selectedIndex.value == 3) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                ""
            )
        },
            label = { Text(text = "Favorite") },
            selected = (selectedIndex.value == 3),
            onClick = {
                selectedIndex.value = 3
                onSelectedIndexChanged(selectedIndex.value)

            })

        NavigationBarItem(icon = {
            Icon(
                imageVector = if (selectedIndex.value == 4) Icons.Default.Person else Icons.Outlined.Person,
                ""
            )
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 4),
            onClick = {
                selectedIndex.value = 4
                onSelectedIndexChanged(selectedIndex.value)

            })
    }
//
}