package com.faysal.assessment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.faysal.assessment.common.HomeRoute
import com.faysal.assessment.common.UserDetailsRoute
import com.faysal.assessment.data.models.UserPosts
import com.faysal.assessment.ui.screens.HomeScreen
import com.faysal.assessment.ui.screens.UserDetailsScreen
import com.faysal.assessment.ui.theme.FaysalsAssessmentTheme
import com.faysal.assessment.ui.viewmodels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FaysalsAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Log.d("DF", "onCreate: $innerPadding")
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                navController = navController, viewModel = viewModel
                            )
                        }
                        composable<UserDetailsRoute> {
                            val arguments = it.toRoute<UserDetailsRoute>()
                            val userPost = Gson().fromJson(arguments.userPostsJson, UserPosts::class.java)
                            UserDetailsScreen(
                                navController = navController,
                                userPosts = userPost
                            )
                        }
                    }
                }
            }
        }
    }
}