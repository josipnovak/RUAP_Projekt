package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.data.DatabaseModel
import hr.ferit.josipnovak.myapplication.data.PlayerData
import hr.ferit.josipnovak.myapplication.ui.theme.CustomButtonColors

@Composable
fun ComparePlayers(
    navController: NavController,
    viewModel: DatabaseModel,
    player1: PlayerData?,
    player2: PlayerData?
) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Black.copy(alpha = 0.1f)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        player1?.let {
                            Text(
                                text = it.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        } ?: run {
                            Button(
                                onClick = {
                                    //navController.navigate("playersList")
                                },
                                colors = CustomButtonColors()
                            ) {
                                Text("Add player 1", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Comparison", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        player2?.let {
                            Text(
                                text = it.name,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        } ?: run {
                            Button(
                                onClick = {
                                    //navController.navigate("playersList")
                                },
                                colors = CustomButtonColors()
                            ) {
                                Text("Add player 2", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}