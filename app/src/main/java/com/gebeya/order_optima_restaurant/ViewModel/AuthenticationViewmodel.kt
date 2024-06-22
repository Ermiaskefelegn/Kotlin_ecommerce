package com.gebeya.order_optima_restaurant.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gebeya.order_optima_restaurant.data.network.model.UserModel
import com.gebeya.order_optima_restaurant.domain.repository.APIRepositoryDef
import com.gebeya.order_optima_restaurant.domain.repository.PreferencesRepository
import com.gebeya.order_optima_restaurant.domain.repository.ResultData
import com.gebeya.order_optima_restaurant.ui.theme.screens.Screens
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class AuthenticationViewmodel @Inject constructor(
    private val APIRepositoryDef: APIRepositoryDef,
    private val preferencesRepository: PreferencesRepository,

    ) : ViewModel() {

    var roleId by mutableStateOf("")
    var UserDetail by mutableStateOf(UserModel())
    var loading: Boolean by mutableStateOf(false)
    var isloading: Boolean by mutableStateOf(false)

    fun checkUserStatus(navController: NavController) {
        val tokenValue = preferencesRepository.getAuthenticationToken();
        if (tokenValue != null) {
//            println("token value  $tokenValue")
            navController.navigate(Screens.MainScreen.route)
        } else {
            navController.navigate(Screens.LoginScreen.route)
        }
    }

    fun getToken(): String {
        roleId = preferencesRepository.getAuthenticationToken()?.token ?: "";
        return roleId
    }

    suspend fun getUser(userId: Int): ResultData<UserModel> {
        loading = true
        return when (val result = APIRepositoryDef.getUser(userId)) {
            is ResultData.Fail -> {
                loading = false
                result
            }
            is ResultData.Success -> {
                loading = false
                result
            }
        }
    }

    fun login(username: String, password: String, navController: NavController) {
        viewModelScope.launch {
            isloading=true
            when (val result = APIRepositoryDef.login(username, password)) {
                is ResultData.Fail -> {
                    println("$username , $password")
                    println("Login result: ${result.errorMessage}")
                }

                is ResultData.Success -> {
                    navController.navigate(Screens.MainScreen.route)
                    println("Login result: ${result.data}")
                    roleId = preferencesRepository.getAuthenticationToken()?.token ?: "";

                }
            }
            isloading=false

        }
    }

    fun logout(navController: NavController) {
        viewModelScope.launch {
            preferencesRepository.deleteAuthenticationToken()
            navController.navigate(Screens.LoginScreen.route)

        }

    }

    fun register(userModel: UserModel, navController: NavController) {
        viewModelScope.launch {
            when (val result = APIRepositoryDef.registerUser(userModel)) {
                is ResultData.Fail -> {

                    println("Login result: ${result.errorMessage}")
                    println("Login result: $userModel")
                    val gson = Gson()
                    val json = gson.toJson(userModel)
                    println("Request JSON: $json")

                }

                is ResultData.Success -> {
                    navController.navigate(Screens.LoginScreen.route)
                    println("Login result: ${result.data}")

                }
            }
        }
    }

}