package com.gebeya.order_optima_restaurant.ui.theme.componenets

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.gebeya.order_optima_restaurant.R

object MyIcons {
    val List = Icons.AutoMirrored.Rounded.List
    val Info = Icons.Rounded.Info
    val AccountBox = Icons.Default.AccountBox
    val Location = Icons.Rounded.LocationOn
    val ArrowBack = Icons.AutoMirrored.Filled.ArrowBack
    val Search = Icons.Filled.Search
    val MoreVert = Icons.Filled.MoreVert
    val Star = Icons.Filled.Star
    val Email = Icons.Filled.Email
    val Share = Icons.Filled.Share
    val Edit = Icons.Filled.Edit
    val KeyboardArrowRight = Icons.AutoMirrored.Filled.KeyboardArrowRight

    val AppIcon = R.drawable.ic_launcher_background
//    val Policy = R.drawable.ic_policy_24dp
}
sealed class DCodeIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : DCodeIcon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : DCodeIcon()
}