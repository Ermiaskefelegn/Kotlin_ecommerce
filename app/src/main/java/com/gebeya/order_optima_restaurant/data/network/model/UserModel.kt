package com.gebeya.order_optima_restaurant.data.network.model

data class UserModel(
    val address: Address = Address(
        city = "",
        street = "",
        zipcode = "",
        geolocation = Geolocation(lat = "", long = ""),
        number = 0
    ),
    val email: String = "",
    val name: Name = Name(firstname = "", lastname = ""),
    val password: String = "",
    val phone: String = "",
    val username: String = ""
)