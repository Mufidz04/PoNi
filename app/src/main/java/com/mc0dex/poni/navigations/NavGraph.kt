package com.mc0dex.poni.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mc0dex.poni.ui.screens.AlumniDetailScreen
import com.mc0dex.poni.ui.screens.AlumniFormScreen
import com.mc0dex.poni.ui.screens.AlumniListScreen
import com.mc0dex.poni.ui.screens.BeritaDetailScreen
import com.mc0dex.poni.ui.screens.BeritaListScreen
import com.mc0dex.poni.ui.screens.HomeScreen
import com.mc0dex.poni.ui.screens.LoginScreen
import com.mc0dex.poni.ui.screens.ProfileScreen
import com.mc0dex.poni.ui.screens.SplashScreen
import com.mc0dex.poni.ui.viewmodel.BeritaViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "splash_screen") {
        composable("alumniList") { AlumniListScreen(navController) }
        composable("alumniDetail/{alumniId}") { backStackEntry ->
            AlumniDetailScreen(navController, backStackEntry)
        }
        composable("alumniForm") {
            AlumniFormScreen(navController)
        }
        composable("alumniForm/{alumniId}") { backStackEntry ->
            AlumniFormScreen(navController)
        }
        composable("editAlumni/{alumniId}") { backStackEntry ->
            val alumniId = backStackEntry.arguments?.getString("alumniId")?.toIntOrNull()
            AlumniFormScreen(
                navController = navController,
                alumniId = alumniId
            )
        }
        composable("add_alumni_data") { AlumniFormScreen(navController) }
        composable("berita_list_screen") {
            BeritaListScreen(navController)
        }
        composable(
            route = "detail_berita_screen/{beritaId}",
            arguments = listOf(navArgument("beritaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val beritaId = backStackEntry.arguments?.getInt("beritaId") ?: return@composable
            BeritaDetailScreen(navController = navController, beritaId = beritaId)
        }
        composable("profile_screen") { ProfileScreen(navController) } // Create this screen
        composable("home_screen") { HomeScreen(navController) } // Create this screen
        composable("splash_screen") { SplashScreen(navController) } // Create this screen
        composable("login_screen") { LoginScreen(navController) } // Create this screen
    }
}
