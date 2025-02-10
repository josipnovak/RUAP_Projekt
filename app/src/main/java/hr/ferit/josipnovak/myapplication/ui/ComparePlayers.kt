package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.data.PlayerData
import hr.ferit.josipnovak.myapplication.ui.theme.CustomButtonColors

@Composable
fun ComparePlayers(
    navController: NavController,
    viewModel: DatabaseModel,
    player1Id: Int,
    player2Id: Int
) {
    val player1 by produceState<PlayerData?>(initialValue = null) {
        value = viewModel.getPlayerById(player1Id)
    }
    val player2 by produceState<PlayerData?>(initialValue = null) {
        value = viewModel.getPlayerById(player2Id)
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
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Player Comparison",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                PlayerColumn(player = player1, navController, "Add Player 1", player1Id, player2Id)
                PlayerColumn(player = player2, navController, "Add Player 2", player1Id, player2Id)
            }
        }
    }
}

@Composable
fun PlayerColumn(player: PlayerData?, navController: NavController, buttonText: String, player1Id: Int, player2Id: Int) {
    player?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.White, RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = it.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = "Age: ${it.age}", color = Color.White, fontSize = 16.sp)
            Text(text = "Height: ${it.height} cm", color = Color.White, fontSize = 16.sp)
            Text(text = "Position: ${it.position}", color = Color.White, fontSize = 16.sp)
            Text(text = "Club: ${it.club}", color = Color.White, fontSize = 16.sp)
            Text(text = "Nationality: ${it.nationality}", color = Color.White, fontSize = 16.sp)
            Text(text = "Max value: ${it.maxPrice} mil €", color = Color.White, fontSize = 16.sp)
            Text(text = "Calculated value: ${it.calculatedValue} mil €", color = Color.White, fontSize = 16.sp)
        }
    }?: run {
        Button(
            onClick = { navController.navigate("player_selection/$player1Id/$player2Id") },
            colors = CustomButtonColors()
        ) {
            Text(buttonText, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
