package com.gebeya.order_optima_restaurant.data.network.entity




data class OrderDetailModel (
    val id: Long? = null,
    val restaurant: OrderRestaurant? = null,
    val orderStatus: String? = null,
    val totalPrice: Double? = null,
    val orderItems: List<OrderDetailItem>? = null,
    val deliveryAddress: String? = null
)

data class OrderDetailItem (
    val id: Long? = null,
    val product: OrderProduct? = null,
    val quantity: Long? = null,
    val price: Double? = null
)

data class OrderProduct (
    val id: Long? = null,
    val name: String? = null,
    val inventoryID: Any? = null,
    val quantity: Long? = null,
    val price: Double? = null,
    val imageURL: String? = null,
    val discount: String? = null,
    val calories: String? = null,
    val fat: String? = null,
    val protein: String? = null,
    val weight: String? = null,
    val size: String? = null,
    val volume: String? = null,
    val brands: String? = null,
    val category: OrderCategory? = null,
    val reviews: List<Any?>? = null,
    val description: String? = null
)

data class OrderCategory (
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val imageURL: Any? = null
)

data class OrderRestaurant (
    val id: Long? = null,
    val email: String? = null,
    val addresses: List<OrderAddress>? = null,
    val status: String? = null,
    val phoneNumber: List<OrderPhoneNumber>? = null,
    val profilePictureURL: Any? = null,
    val businessName: String? = null,
    val licenseNumber: String? = null,
    val ownerName: String? = null
)

data class OrderAddress (
    val id: Long? = null,
    val city: String? = null,
    val subCity: String? = null,
    val wereda: String? = null
)

data class OrderPhoneNumber (
    val id: Long? = null,
    val phoneNumber: String? = null
)
