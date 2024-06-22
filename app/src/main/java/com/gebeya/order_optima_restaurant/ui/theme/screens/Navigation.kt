package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gebeya.order_optima_restaurant.ViewModel.AuthenticationViewmodel
import com.gebeya.order_optima_restaurant.ViewModel.HomeViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val canNavBack = remember { mutableStateOf(false) }
    val authenticationViewmodel = hiltViewModel<AuthenticationViewmodel>()
    val homeViewModel = hiltViewModel<HomeViewModel>()


    NavHost(navController = navController, startDestination = Screens.ShoppingApp.route) {
        composable(route = Screens.ShoppingApp.route) {
            canNavBack.value = navController.previousBackStackEntry != null
            ShoppingApp()
            authenticationViewmodel.checkUserStatus(navController = navController)
        }

        composable(route = Screens.LoginScreen.route) { backStackEntry ->

            canNavBack.value = navController.previousBackStackEntry != null
            LoginScreen(
                navController = navController
            )
        }
        composable(route = Screens.MainScreen.route) { backStackEntry ->

            canNavBack.value = navController.previousBackStackEntry != null
            MainScreen(
                navController = navController,
            )
        }
        composable(route = Screens.RegistrationScreen.route) { backStackEntry ->

            canNavBack.value = navController.previousBackStackEntry != null
            RegisterUser(
                navController = navController
            )
        }


        composable(route = Screens.ProfileScreen.route) { backStackEntry ->

            canNavBack.value = navController.previousBackStackEntry != null
            ProfileScreen(
                navController = navController
            )
        }

        composable(
            route = "categories-screen/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")

            canNavBack.value = navController.previousBackStackEntry != null
            if (category != null) {
                CategoryDetails(
                    navController = navController,
                    categoryId = category
                )
            }
        }



        composable(
            route = "product-screen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = homeViewModel.productList[productId ?: 0]
            ProductDetails(product, navController)
        }

        composable(
            route = "product-screen2/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            productId?.let {
                ProductDetailsCat(productId = it, navController = navController)
            }
        }
    }
}