package com.faysal.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.faysal.assessment.common.HomeRoute
import com.faysal.assessment.common.UserDetailsRoute
import com.faysal.assessment.ui.screens.HomeScreen
import com.faysal.assessment.ui.screens.UserDetailsScreen
import com.faysal.assessment.ui.theme.FaysalsAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaysalsAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                navController = navController
                            )
                        }
                        composable<UserDetailsRoute> {
                            val arguments = it.toRoute<UserDetailsRoute>()
                            UserDetailsScreen(
                                navController = navController,
                                arguments.posts
                            )
                        }
                    }
                }
            }
        }
    }
}