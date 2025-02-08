package hr.ferit.josipnovak.myapplication

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.data.PlayerData
import hr.ferit.josipnovak.myapplication.ui.CalculatedValue
import hr.ferit.josipnovak.myapplication.ui.CalculateValue
import hr.ferit.josipnovak.myapplication.ui.FeaturedPlayers
import hr.ferit.josipnovak.myapplication.ui.HomeScreen
import hr.ferit.josipnovak.myapplication.ui.LandingScreen
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.json.Json


object Paths {
    const val LANDING_SCREEN = "landing_screen"
    const val HOME_SCREEN = "home_screen"
    const val CALCULATE_VALUE = "calculate_value"
    const val CALCULATED_VALUE = "calculated_value"
    const val FEATURED_PLAYERS = "featured_players"
    const val FEATURED_PLAYER = "featured_player/{playerId}"
    const val COMPARE_PLAYERS = "compare_players"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationController(
    viewModel: DatabaseModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Paths.LANDING_SCREEN) {
        composable(Paths.LANDING_SCREEN) {
            LandingScreen(navController = navController)
        }
        composable(Paths.HOME_SCREEN) {
            HomeScreen(navController = navController)
        }
        composable(Paths.CALCULATE_VALUE) {
            CalculateValue(navController = navController)
        }
        composable(
            "${Paths.CALCULATED_VALUE}/{playerData}",
            arguments = listOf(
                navArgument("playerData") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val jsonString = backStackEntry.arguments?.getString("playerData")
            val playerData = jsonString?.let { Json.decodeFromString<PlayerData>(it) }
            playerData?.let { CalculatedValue(navController = navController, playerData = it) }
        }
        composable(Paths.FEATURED_PLAYERS){
            FeaturedPlayers(navController = navController, viewModel = viewModel)
        }
    }
}
