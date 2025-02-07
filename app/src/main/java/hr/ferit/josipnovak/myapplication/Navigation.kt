package hr.ferit.josipnovak.myapplication

import CalculateValue
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventStart
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.ui.CalculatedValue
import hr.ferit.josipnovak.myapplication.ui.HomeScreen
import hr.ferit.josipnovak.myapplication.ui.LandingScreen

object Paths{
    const val LANDING_SCREEN = "landing_screen"
    const val HOME_SCREEN = "home_screen"
    const val CALCULATE_VALUE = "calculate_value"
    const val CALCULATED_VALUE = "calculated_value/{value}"
    const val ADD_FEATURED_PLAYER = "add_featured_player"
    const val FEATURED_PLAYER = "featured_player/{playerId}"
    const val COMPARE_PLAYERS = "compare_players"
    fun getFeaturedPlayerPath(playerId: Int): String{
        if(playerId != null && playerId != -1){
            return "featured_player/$playerId"
        }
        return "featured_player/{playerId}"
    }
    fun getCalculatedValuePath(value: Int): String{
        if(value != null && value != -1){
            return "calculated_value/$value"
        }
        return "calculated_value/{value}"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationController(
    viewModel: DatabaseModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Paths.CALCULATE_VALUE){
        composable(Paths.LANDING_SCREEN){
            LandingScreen(navController = navController)
        }
        composable(Paths.HOME_SCREEN){
            HomeScreen(navController= navController)
        }
        composable(Paths.CALCULATE_VALUE){
            CalculateValue(navController = navController)
        }
        composable(
            Paths.CALCULATED_VALUE,
            arguments = listOf(
                navArgument("value") {
                    type = NavType.IntType
                }
        )
        ){ backStackEntry ->
            val value = backStackEntry.arguments?.getInt("value")
            CalculatedValue(navController = navController, value = value!!.toInt())
        }
    }
}