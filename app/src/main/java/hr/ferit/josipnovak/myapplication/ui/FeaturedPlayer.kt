package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun FeaturedPlayer(
    navController: NavController,
    viewModel: DatabaseModel,
    playerId: Int
) {
    val player by produceState<PlayerData?>(initialValue = null) {
        value = viewModel.getPlayerById(playerId)
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black.copy(alpha = 0.70f))
                .padding(20.dp),
        ) {
            item {
                Text(
                    text = "Player Information",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
                player?.let { player ->
                    InfoText("Name", player.name)
                    InfoText("Age", player.age.toString())
                    InfoText("Height", player.height)
                    InfoText("Nationality", player.nationality)
                    InfoText("Max Price", player.maxPrice.toString())
                    InfoText("Position", player.position)
                    InfoText("Shirt Number", player.shirtNr.toString())
                    InfoText("Foot", player.foot)
                    InfoText("League", player.league)
                    InfoText("Club", player.club)
                    InfoText("Outfitter", player.outfitter)
                    InfoText("Contract Expiration", player.contractExpiresDays.toString())
                    InfoText("Contract Joined", player.joinedClubDays.toString())
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Calculated cost for the player",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                InfoText("Calculated Value", player?.calculatedValue.toString())

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            navController.navigate("change_featured_player/$playerId")
                        },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxSize(),
                        colors = CustomButtonColors()
                    ) {
                        Text(
                            "Calculate value with different data",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}