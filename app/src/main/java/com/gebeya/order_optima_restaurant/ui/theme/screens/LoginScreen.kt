package com.gebeya.order_optima_restaurant.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gebeya.order_optima_restaurant.ViewModel.AuthenticationViewmodel
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomButton
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomPasswordInput
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomTextInput

@Composable
fun LoginScreen(
    navController: NavController
) {
    val authenticationViewmodel = hiltViewModel<AuthenticationViewmodel>()
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by remember { mutableStateOf("") }

    // State to manage progress indicator visibility

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp)
            .background(color = Color(0xFFF7F7F7)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .clip(shape = RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome", style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Please Login", style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextInput(
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(300.dp), text = username, label = "UserName", onTextChange = {
                    username = it
                })

            CustomPasswordInput(
                modifier = Modifier
                    .width(300.dp)
                    .padding(vertical = 8.dp),
                password = password,
                label = "Password",
                onPasswordChange = { newPassword ->
                    password = newPassword
                }
            )

            Row {
                Text(
                    text = "Don’t Have Account?", style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.navigate(Screens.RegistrationScreen.route)
                        }
                    ),
                    text = "Register", style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
            }

            // Button with progress indicator
            if (authenticationViewmodel.isloading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(color = Color(0xFF021A35))
                }
            }else{
                CustomButton(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)
                        .padding(top = 50.dp),
                    text = "Login",
                    onClick = {
                        // Show progress indicator

                        // Perform login operation
                        authenticationViewmodel.login(username = username, password = password, navController)

                    }
                )
            }


            Spacer(modifier = Modifier.height(30.dp))

            // Circular progress indicator

        }
    }
}