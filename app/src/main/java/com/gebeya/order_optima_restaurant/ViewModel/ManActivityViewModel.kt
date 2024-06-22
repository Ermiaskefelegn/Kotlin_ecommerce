package com.gebeya.order_optima_restaurant.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ManActivityViewModel @Inject constructor(

) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set


    init {

        viewModelScope.launch {
            delay(300)
            splashCondition = false
        }
        println(splashCondition)
    }
}