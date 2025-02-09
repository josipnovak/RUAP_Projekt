package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.data.PlayerData

@Composable
fun FeaturedPlayers(
    navController: NavController,
    viewModel: DatabaseModel
) {
    var playersList by remember { mutableStateOf<List<PlayerData>>(emptyList()) }
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_2),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 20.dp),
        ) {
            Text(
                text = "Featured Players",
                fontSize = 19.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp, bottom = 16.dp).align(
                    Alignment.CenterHorizontally),
                color = Color.White
            )
            playersList.forEach { player ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable {
                            println("Clicked on player: ${player.name}")
                        }
                        .clip(RoundedCornerShape(10.dp)),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.6f),
                        contentColor = Color.Transparent
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "${player.calculatedValue} mil €",
                            style = TextStyle(
                                color = Color.White, // White text for predicted price
                                fontSize = 16.sp
                            )
                        )
                        Divider(
                            color = Color.White,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp).width(60.dp)
                        )
                        Text(
                            text = player.name,
                            style = TextStyle(
                                color = Color.White, // White text inside the card
                                fontSize = 24.sp
                            )
                        )
                    }
                }
            }
        }
    }
}