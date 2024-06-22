package com.gebeya.order_optima_restaurant.ui.theme.screens

sealed class Screens(
    val route: String,
) {
    object ShoppingApp : Screens("start-screen")
    object MainScreen : Screens("main-screen")
    object CartScreen : Screens("cart-screen")

    object Categoriesdetails : Screens("categories-screen")

    object FavoriteScreen : Screens("favorite-screen")

    object LoginScreen : Screens("login-screen")

    object HomeScreen : Screens("home-screen")

    object RegistrationScreen : Screens("register-screen")

    object ProfileScreen : Screens("profile-screen")
    object ProductScreen : Screens("product-screen")
    object ProductScreen2 : Screens("product-screen2")

    object OrderListScreen : Screens("orderList-screen")
}
