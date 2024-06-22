package com.gebeya.order_optima_restaurant.ui.theme.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    onRatingChanged: (Int) -> Unit = {},

) {
    Row(modifier = modifier.background(          Color.White,
    )) {
        for (i in 1..5) {
            val icon = if (i <= rating) Icons.Filled.Star else Icons.Filled.StarBorder
            Image(
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0xFF021A35)),
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    var rating by remember { mutableStateOf(3.0) }
    Surface {
        RatingBar(rating = rating) {
            rating = it.toDouble()
        }
    }
}

