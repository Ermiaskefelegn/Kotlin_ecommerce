package com.gebeya.order_optima_restaurant.data.network.model

data class Address(
    val city: String,
    val geolocation: Geolocation,
    val number: Int,
    val street: String,
    val zipcode: String
)