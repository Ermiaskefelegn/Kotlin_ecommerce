package com.gebeya.order_optima_restaurant.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import com.gebeya.order_optima_restaurant.ViewModel.AuthenticationViewmodel
import com.gebeya.order_optima_restaurant.ui.theme.componenets.DCodeIcon
import com.gebeya.order_optima_restaurant.ui.theme.componenets.MyIcons


@Composable
fun ProfileScreen(navController: NavController) {
    val authenticationViewmodel = hiltViewModel<AuthenticationViewmodel>()
    val token = authenticationViewmodel.getToken()

    LaunchedEffect(token) {
        if (token.isNotBlank()) {
            try {
                val decodedToken = JWT.decode(token)
                val userId = decodedToken.subject.toInt()

                // Fetch user details asynchronously
                authenticationViewmodel.getUser(userId)?.let { userResult ->
                    if (userResult.data != null) {
                        authenticationViewmodel.UserDetail = userResult.data
                    } else {
                        // Handle case where userResult.data is null
                        Log.e("ProfileScreen", "User data is null")
                    }
                }
            } catch (e: JWTDecodeException) {
                // Handle JWT decoding exception
                Log.e("ProfileScreen", "JWT decoding exception: ${e.message}")
            } catch (e: Exception) {
                // Handle other exceptions (e.g., network failure)
                Log.e("ProfileScreen", "Failed to fetch user details: ${e.message}")
            }
        } else {
            // Handle case where token is null or empty
            Log.e("ProfileScreen", "Token is null or empty")
        }
    }


    println("token " + authenticationViewmodel.UserDetail.name)
    ProfileScreenUI(
        authenticationViewmodel = authenticationViewmodel,
        navController = navController
    )
}

const val my_description =
    "A group of simple, open source Android apps without ads and unnecessary permissions, with materials design UI."

data class FeatureList(
    val name: String,
    val listIcon: DCodeIcon,
    val onClick: () -> Unit = {}
)


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenUI(
    authenticationViewmodel: AuthenticationViewmodel,
    navController: NavController
) {

    Scaffold(modifier = Modifier.semantics {
        testTagsAsResourceId = true
    }, containerColor = Color.White, topBar = {
        TopAppBar(
            title = {
                Text(text = "Profile")
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(MyIcons.Search, contentDescription = "Search")
                }
                IconButton(onClick = { }) {
                    Icon(MyIcons.MoreVert, contentDescription = "More")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
        )
    }) { padding ->

        ProfileContent(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            TopProfileLayout(authenticationViewmodel)

            FooterContent(
                authenticationViewmodel = authenticationViewmodel,
                navController = navController
            )
        }
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        content()
    }
}

@Composable
fun TopProfileLayout(authenticationViewmodel: AuthenticationViewmodel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = Color.White),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = DCodeIcon.DrawableResourceIcon(MyIcons.AppIcon).id),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = authenticationViewmodel.UserDetail.name.firstname + " " + authenticationViewmodel.UserDetail.name.lastname,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = authenticationViewmodel.UserDetail.email,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }




        }

    }
}

@Composable
fun FooterContent(authenticationViewmodel: AuthenticationViewmodel, navController: NavController) {
    val moreOptionsList = listOf(
        FeatureList("Edit Profile", DCodeIcon.ImageVectorIcon(MyIcons.Edit)),
        FeatureList("Manage Account", DCodeIcon.ImageVectorIcon(MyIcons.AccountBox)),
        FeatureList("Privacy Policy", DCodeIcon.ImageVectorIcon(Icons.Default.Policy)),
        FeatureList("About", DCodeIcon.ImageVectorIcon(MyIcons.Info)),
        FeatureList("Help & Feedback", DCodeIcon.ImageVectorIcon(Icons.AutoMirrored.Filled.Help)),
        FeatureList("Logout", DCodeIcon.ImageVectorIcon(Icons.AutoMirrored.Filled.Logout)) {
            authenticationViewmodel.logout(navController = navController)
        }


    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        shape = RoundedCornerShape(2),
    ) {
        Column(modifier = Modifier.padding(5.dp).background(Color.White)) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Footer",
                style = MaterialTheme.typography.titleMedium, color = Color(0xFF021A35)
            )
            moreOptionsList.forEach {
                MoreOptionsComp(it)
            }
        }
    }
}

@Composable
fun MoreOptionsComp(
    featureList: FeatureList,
) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .background(Color.White)
            .clickable { featureList.onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (featureList.listIcon) {
            is DCodeIcon.ImageVectorIcon -> Icon(
                tint = Color(0xFF021A35),
                imageVector = featureList.listIcon.imageVector,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )

            is DCodeIcon.DrawableResourceIcon -> Icon(
                tint = Color(0xFF021A35),
                painter = painterResource(id = featureList.listIcon.id),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .weight(1f)
        ) {
            Text(
                text = featureList.name,
                style = MaterialTheme.typography.labelLarge,
                color = Color(0xFF021A35),

                )
        }
        Icon(
            imageVector = MyIcons.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(4.dp)
        )
    }
}



