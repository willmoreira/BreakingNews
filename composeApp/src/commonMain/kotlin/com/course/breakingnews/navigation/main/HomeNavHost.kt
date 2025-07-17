package com.course.breakingnews.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.course.breakingnews.features.about.screen.AboutScreen
import com.course.breakingnews.features.home.screen.HomeScreen
import com.course.breakingnews.features.details.screen.DetailsScreen
import com.course.breakingnews.navigation.routes.HomeRoutes
import com.course.breakingnews.navigation.routes.HomeRoutes.About

@Composable
fun HomeNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeRoutes.Home,
        modifier = modifier
    ) {
        composable<About> {
            AboutScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<HomeRoutes.Home> {
            HomeScreen(
                navigateToDetailsScreen = { urlToImage, description ->
                    navHostController.navigate(HomeRoutes.Details(
                        urlToImage = urlToImage,
                        description = description
                    ))
                },
                navigateToAboutScreen = {
                    navHostController.navigate(About)
                }
            )
        }

        composable<HomeRoutes.Details> {
            DetailsScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}