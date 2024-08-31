package com.faysal.assessment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.faysal.assessment.R
import com.faysal.assessment.common.UserDetailsRoute
import com.faysal.assessment.common.findActivity
import com.faysal.assessment.data.models.UserPosts
import com.faysal.assessment.ui.theme.Nunito
import com.faysal.assessment.ui.ui_states.HomeUiStates
import com.faysal.assessment.ui.viewmodels.MainViewModel
import com.faysal.assessment.ui.widgets.UserCardWidget
import com.faysal.assessment.ui.widgets.UserLoading
import com.google.gson.Gson

@Composable
fun HomeScreen(
    navController: NavHostController? = null,
    viewModel: MainViewModel? = null
) {

    val view = LocalView.current
    val context = LocalContext.current
    val activity = context.findActivity()

    val darkTheme = isSystemInDarkTheme()

    val navigationColor = MaterialTheme.colorScheme.surface
    val statusColor = MaterialTheme.colorScheme.primary

    SideEffect {
        activity?.let {
            with(it.window) {
                statusBarColor = statusColor.toArgb()
                navigationBarColor = navigationColor.toArgb()
                WindowCompat.getInsetsController(
                    this, view
                ).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(
                    this, view
                ).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (viewModel?.homeUiStates?.value !is HomeUiStates.Success) {
            viewModel?.getUserPosts()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .height(55.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name).uppercase(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = Nunito,
                    fontSize = 16.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }


            when (val homeUiStates = viewModel?.homeUiStates?.value) {
                is HomeUiStates.Success -> {
                    if (homeUiStates.userPosts.isEmpty()) {
                        ErrorLayout(message = stringResource(id = R.string.txt_no_data_found))
                    } else {
                        HomeBodyContent(
                            userPosts = homeUiStates.userPosts,
                            navController = navController
                        )
                    }
                }

                is HomeUiStates.Error -> {
                    val errorMessage = homeUiStates.error
                    ErrorLayout(message = errorMessage)
                }

                is HomeUiStates.Loading -> {
                    LoadingLayout()
                }

                else -> Unit
            }
        }
    }
}

@Composable
fun LoadingLayout() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        items(10) {
            UserLoading(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onUserClick = {}
            )
        }
    }
}


@Composable
fun HomeBodyContent(
    userPosts: List<UserPosts>,
    navController: NavHostController?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        item(
            key = "top_spacing"
        ) { Spacer(modifier = Modifier.height(10.dp)) }

        itemsIndexed(
            items = userPosts,
            key = { _, userPost -> userPost.user.userId }
        ) { _, userPost ->
            UserCardWidget(
                userPost = userPost,
                onUserClick = {
                    val userPostJson = Gson().toJson(userPost)
                    navController?.navigate(UserDetailsRoute(userPostJson))
                }
            )
        }


        item(
            key = "bottom_spacing"
        ) { Spacer(modifier = Modifier.height(10.dp)) }
    }
}

@Composable
fun ErrorLayout(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Nunito
        )
    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = null)
}