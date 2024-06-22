package com.gebeya.eventnotifier.data.SharedPreferences

import android.app.Application
import androidx.preference.PreferenceManager
import com.gebeya.order_optima_restaurant.data.network.entity.AuthenticationToken
import com.gebeya.order_optima_restaurant.domain.repository.PreferencesRepository
import com.google.gson.Gson

class PreferencesRepositoryImpl(
    application: Application
): PreferencesRepository {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    private val gson = Gson()

    override fun saveAuthenticationToken(authenticationToken: AuthenticationToken) {
        val tokenJson = gson.toJson(authenticationToken)
        println("Login result json: $tokenJson")
        sharedPreferences.edit().putString("authentication_token",tokenJson).apply()
    }

    override fun deleteAuthenticationToken() {

        sharedPreferences.edit().remove("authentication_token").apply()
    }

    override fun getAuthenticationToken(): AuthenticationToken? {
        val tokenJson = sharedPreferences.getString("authentication_token", null)
        if(tokenJson != null){
            return gson.fromJson(tokenJson, AuthenticationToken::class.java)
        }else{
            return null
        }
    }
}