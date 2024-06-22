package com.gebeya.order_optima_restaurant.ui.theme.componenets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.twotone.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomTextInput(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
) {
    OutlinedTextField(

        value = text, onValueChange = onTextChange,
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color.White
//        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF021A35),
//             = Color.White

            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,

            ),
        maxLines = maxLine, label = {
            Text(text = label)
        }, keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text


        ), shape = RoundedCornerShape(10.dp), modifier = modifier

    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomTextNumberInput(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = text, onValueChange = onTextChange, shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF021A35),

            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,

            ),
        maxLines = maxLine, label = {
            Text(text = label)
        }, keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done, keyboardType = KeyboardType.Phone
        ), modifier = modifier

    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomPasswordInput(
    modifier: Modifier = Modifier,
    password: String,
    label: String,
    maxLine: Int = 1,
    onPasswordChange: (String) -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val visualTransformation =
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = password,

        onValueChange = onPasswordChange,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF021A35),

            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,

        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                }, modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Outlined.Visibility else Icons.TwoTone.VisibilityOff,
                    contentDescription = if (passwordVisibility) "Hide Password" else "Show Password"
                )
            }
        },
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp),
        maxLines = maxLine,
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
        ),
        modifier = modifier,


        )

    // Toggle button for password visibility

}