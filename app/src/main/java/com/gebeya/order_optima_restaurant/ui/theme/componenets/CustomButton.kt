package com.gebeya.order_optima_restaurant.ui.theme.componenets

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(
            10.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021A35)),
        enabled = enabled,
        modifier = modifier
    ) {
        Text(
            text = text, color = Color.White,
            fontSize = 20.sp,
            
            fontWeight = FontWeight.Bold,
        )
    }
}