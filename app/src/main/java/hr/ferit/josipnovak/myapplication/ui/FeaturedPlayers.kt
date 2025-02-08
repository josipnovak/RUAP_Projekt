package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.data.PlayerData

@Composable
fun FeaturedPlayers(
    navController: NavController,
    viewModel: DatabaseModel
) {
    var playersList by
    remember { mutableStateOf<List<PlayerData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch players when composable is first composed
    LaunchedEffect(Unit) {
        viewModel.getAllPlayers(
            onSuccess = { players ->
                playersList = players
                isLoading = false
            },
            onFailure = { error ->
                errorMessage = error
                isLoading = false
            }
        )
    }

    // UI Logic based on the loading state
    if (isLoading) {
        // Show loading spinner
        CircularProgressIndicator()
    } else if (errorMessage != null) {
        // Show error message
        Text(
            text = "Error: $errorMessage",
            color = androidx.compose.ui.graphics.Color.Red,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        // Show list of players
        Column(modifier = Modifier.padding(16.dp)) {
            playersList.forEach { player ->
                Text(text = "Name: ${player.name}, Position: ${player.position}")
            }
        }
    }
}