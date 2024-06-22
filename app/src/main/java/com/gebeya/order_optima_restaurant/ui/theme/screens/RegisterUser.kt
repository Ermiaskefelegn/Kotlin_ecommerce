package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gebeya.order_optima_restaurant.ViewModel.AuthenticationViewmodel
import com.gebeya.order_optima_restaurant.data.network.model.Address
import com.gebeya.order_optima_restaurant.data.network.model.Geolocation
import com.gebeya.order_optima_restaurant.data.network.model.Name
import com.gebeya.order_optima_restaurant.data.network.model.UserModel
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomButton
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomPasswordInput
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomTextInput
import com.gebeya.order_optima_restaurant.ui.theme.componenets.CustomTextNumberInput

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterUser(
    navController: NavController
) {
    val authenticationViewmodel = hiltViewModel<AuthenticationViewmodel>()
    val context = LocalContext.current
    var fname by rememberSaveable {
        mutableStateOf("")
    }
    var lname by rememberSaveable {
        mutableStateOf("")
    }
    var username by rememberSaveable {
        mutableStateOf("")
    }

    var city by rememberSaveable {
        mutableStateOf("")
    }
    var zipCode by rememberSaveable {
        mutableStateOf("")
    }
    var street by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
//            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
            .padding(5.dp)
            .background(color = Color(0xFFF7F7F7)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
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
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Please Register", style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(

                modifier = Modifier.width(330.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 8.dp, bottom = 8.dp
                        )
                        .width(150.dp),
                    text = fname,
                    label = "First Name",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                            fname = it
                        }
                    })

                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp, end = 5.dp
                        )
                        .width(150.dp), text = lname, label = "Last Name", onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                            lname = it
                        }
                    })
            }

            Row(

                modifier = Modifier.width(330.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp),
                    text = username,
                    label = "Username",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() || char.isDigit()}) {
                            username = it
                        }
                    })
//
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp), text = city, label = "City", onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                            city = it
                        }
                    })
            }
            Row(

                modifier = Modifier.width(330.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp), text = zipCode, label = "Zipcode", onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                            zipCode = it
                        }
                    })
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp), text = street, label = "Street", onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                            street = it
                        }
                    })
            }

            Row(

                modifier = Modifier.width(330.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                CustomTextInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp), text = email, label = "Email", onTextChange = {
                        email = it
//                        if (it.all { char -> char.isLetter() || char.isWhitespace() || char.isDigit() || char.equals("@,.") }) {
//                            email = it
//                        }
                    })


                CustomTextNumberInput(
                    Modifier
                        .padding(
                            top = 9.dp, bottom = 8.dp
                        )
                        .width(150.dp), text = phone, label = "Phone Number", onTextChange = {
                        phone = it
//                        if (it.all { char -> char.isDigit() || char.isWhitespace() }) {
//                            phone = it
//                        }
                    })
            }
            CustomPasswordInput(
                modifier = Modifier
                    .width(310.dp)
                    .padding(vertical = 8.dp),
                password = password,
                label = "Password",
                onPasswordChange = { newPassword ->
                    password = newPassword
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Text(
                    text = "Don't You Have an Account?",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.navigate(Screens.LoginScreen.route)
                        }
                    ),
                    text = "Login", style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                    )
                )
            }


//        val companyId = UUID.randomUUID()
            CustomButton(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .padding(top = 50.dp),
                text = "Register",
                onClick = {
                    authenticationViewmodel.register(
                        UserModel(
                            email = email,
                            address =
                                Address(city = city, zipcode = zipCode, street = street, geolocation = Geolocation(lat = "1.0", long = "1.0",), number = 3 )
                            ,
                            username = username,
                            password = password,
                            phone = phone,
                            name = Name(firstname =fname , lastname =lname ),
                        ),navController


                    )

                })
            Spacer(modifier = Modifier.height(20.dp))

        }

    }
}