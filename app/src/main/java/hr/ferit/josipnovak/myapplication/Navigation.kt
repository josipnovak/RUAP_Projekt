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
import hr.ferit.josipnovak.myapplication.ui.ChangeFeaturedPlayer
import hr.ferit.josipnovak.myapplication.ui.ComparePlayers
import hr.ferit.josipnovak.myapplication.ui.FeaturedPlayer
import hr.ferit.josipnovak.myapplication.ui.FeaturedPlayers
import hr.ferit.josipnovak.myapplication.ui.HomeScreen
import hr.ferit.josipnovak.myapplication.ui.LandingScreen
import hr.ferit.josipnovak.myapplication.ui.PlayerSelection
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.json.Json


object Paths {
    const val LANDING_SCREEN = "landing_screen"
    const val HOME_SCREEN = "home_screen"
    const val CALCULATE_VALUE = "calculate_value"
    const val CALCULATED_VALUE = "calculated_value"
    const val FEATURED_PLAYERS = "featured_players"
    const val FEATURED_PLAYER = "featured_player/{playerId}"
    const val CHANGE_FEATURED_PLAYER = "change_featured_player/{playerId}"
    const val COMPARE_PLAYERS = "compare_players/{player1Id}/{player2Id}"
    const val PLAYER_SELECTION = "player_selection/{player1Id}/{player2Id}"
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
        composable(Paths.FEATURED_PLAYERS) {
            FeaturedPlayers(navController = navController, viewModel = viewModel)
        }

        composable(
            Paths.FEATURED_PLAYER,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getInt("playerId")
            playerId?.let {
                FeaturedPlayer(
                    navController = navController,
                    viewModel = viewModel,
                    playerId = it
                )
            }
        }
        composable(
            Paths.CHANGE_FEATURED_PLAYER,
            arguments = listOf(
                navArgument("playerId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val playerId = backStackEntry.arguments?.getInt("playerId")
            playerId?.let {
                ChangeFeaturedPlayer(
                    navController = navController,
                    viewModel = viewModel,
                    playerId = it
                )
            }
        }
        composable(
            Paths.COMPARE_PLAYERS,
            arguments = listOf(
                navArgument("player1Id") { type = NavType.IntType },
                navArgument("player2Id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val player1Id = backStackEntry.arguments?.getInt("player1Id") ?: -1
            val player2Id = backStackEntry.arguments?.getInt("player2Id") ?: -1
            ComparePlayers(navController = navController, viewModel = viewModel, player1Id = player1Id, player2Id = player2Id)
        }

        composable(
            Paths.PLAYER_SELECTION,
            arguments = listOf(
                navArgument("player1Id") { type = NavType.IntType },
                navArgument("player2Id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val player1Id = backStackEntry.arguments?.getInt("player1Id") ?: -1
            val player2Id = backStackEntry.arguments?.getInt("player2Id") ?: -1
            PlayerSelection(navController = navController, viewModel = viewModel, player1Id = player1Id, player2Id = player2Id)
        }

    }
}
