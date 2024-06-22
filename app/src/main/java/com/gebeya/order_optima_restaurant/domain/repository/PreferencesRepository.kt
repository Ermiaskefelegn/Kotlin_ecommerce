package com.gebeya.order_optima_restaurant.domain.repository

import com.gebeya.order_optima_restaurant.data.network.model.AuthenticationToken

interface PreferencesRepository {

    fun saveAuthenticationToken(authenticationToken: AuthenticationToken)
    fun getAuthenticationToken(): AuthenticationToken?

    fun deleteAuthenticationToken()
}