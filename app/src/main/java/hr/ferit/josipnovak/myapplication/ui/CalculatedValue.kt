package hr.ferit.josipnovak.myapplication.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject

@Composable
fun CalculatedValue(
    navController: NavController,
    playerData: PlayerData
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
                InfoText("Name", playerData.name.replace("+", " "))
                InfoText("Age", playerData.age.toString())
                InfoText("Height", playerData.height)
                InfoText("Nationality", playerData.nationality.replace("+", " "))
                InfoText("Max Price", playerData.maxPrice.toString().replace("+", " "))
                InfoText("Position", playerData.position.replace("+", " "))
                InfoText("Shirt Number", playerData.shirtNr.toString())
                InfoText("Foot", playerData.foot.replace("+", " "))
                InfoText("League", playerData.league.replace("+", " "))
                InfoText("Club", playerData.club.replace("+", " "))
                InfoText("Outfitter", playerData.outfitter.replace("+", " "))
                InfoText("Contract Expiration", playerData.contractExpiresDays.toString())
                InfoText("Contract Joined", playerData.joinedClubDays.toString())
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

                InfoText("Cost", playerData.calculatedValue.toString(), Color.White)

                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            DatabaseModel().addNewPlayer(playerData)
                            navController.navigate("home_screen")
                        },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .height(48.dp)
                            .fillMaxWidth(),
                        colors = CustomButtonColors()
                    ) {
                        Text("Add player to database", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun InfoText(label: String, value: String, textColor: Color = Color.White) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = "$label:", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Text(text = value, color = textColor, fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}
