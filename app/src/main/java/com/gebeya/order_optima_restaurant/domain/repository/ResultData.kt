package com.gebeya.order_optima_restaurant.domain.repository

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

sealed class ResultData<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T): ResultData<T>(data = data)
    class Fail<T>(errorMessage: String): ResultData<T>(errorMessage = errorMessage)
}



class ResultDataDeserializer<T> : JsonDeserializer<ResultData<T>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ResultData<T> {
        val jsonObject = json!!.asJsonObject

        val dataElement = jsonObject.get("data")
        val errorMessageElement = jsonObject.get("errorMessage")

        return if (dataElement != null && !dataElement.isJsonNull) {
            // Success case
            val data = context!!.deserialize<T>(dataElement, typeOfT)
            ResultData.Success(data)
        } else if (errorMessageElement != null && !errorMessageElement.isJsonNull) {
            // Failure case
            val errorMessage = errorMessageElement.asString
            ResultData.Fail(errorMessage)
        } else {
            // Unexpected case, handle it as per your requirements
            ResultData.Fail("Unexpected error")
        }
    }
}
