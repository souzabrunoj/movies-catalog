package com.moviecatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.screens.detail.DetailScreen
import com.moviecatalog.screens.list.ListScreen
import com.moviecatalog.screens.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object SplashDestination

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val objectId: Int)

@Composable
fun App() {
    MovieTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MovieTheme.colors.backgroundBody),
        ) {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = SplashDestination) {
                composable<SplashDestination> {
                    SplashScreen(
                        onLoadingComplete = {
                            navController.navigate(ListDestination) {
                                popUpTo<SplashDestination> { inclusive = true }
                            }
                        },
                    )
                }
                composable<ListDestination> {
                    ListScreen(navigateToDetails = { objectId ->
                        navController.navigate(DetailDestination(objectId))
                    })
                }
                composable<DetailDestination> { backStackEntry ->
                    DetailScreen(
                        objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                        navigateBack = {
                            navController.popBackStack()
                        },
                    )
                }
            }
        }
    }
}
