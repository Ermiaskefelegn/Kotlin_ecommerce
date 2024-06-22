package com.gebeya.order_optima_restaurant.data.network.entity


typealias Restaurants = ArrayList<RestaurantModel>

data class RestaurantModel(
    val email: String,
    val addresses: List<Address>,
    val userName: String,
    val password: String,
    val phoneNumber: List<PhoneNumber>,
    val businessName: String,
    val ownerName: String,
    val licenseNumber: String
)

data class Address(
    val city: String,
    val subCity: String,
    val wereda: String
)

data class PhoneNumber(
    val phoneNumber: String
)
