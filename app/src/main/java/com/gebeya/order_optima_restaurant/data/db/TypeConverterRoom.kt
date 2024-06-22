package com.gebeya.order_optima_restaurant.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.gebeya.order_optima_restaurant.data.network.model.OrderItem
import com.gebeya.order_optima_restaurant.data.network.model.ProductsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Instant

class TypeConverterRoom {
    @TypeConverter
    fun fromListStringToString(stringList: List<String>): String = stringList.toString()

    @TypeConverter
    fun toListStringFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").split(",")
        for (n in split) {
            result.add(n.trim())
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromInstant(instant: Instant): Long{
        return instant.toEpochMilli()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toInstant(long: Long): Instant{
        return Instant.ofEpochMilli(long)
    }
    @TypeConverter
    fun fromString(value: String?): List<OrderItem>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<OrderItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductsItem>?): String? {
        return if (list == null) {
            null
        } else {
            Gson().toJson(list)
        }
    }
    @TypeConverter
    fun fromProductModel(productsItem: ProductsItem): String {
        return Gson().toJson(productsItem)
    }

    @TypeConverter
    fun toProductModel(json: String): ProductsItem {
        return Gson().fromJson(json, ProductsItem::class.java)
    }
}