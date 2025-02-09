package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.ui.theme.CustomButtonColors

@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_2),
            contentDescription = "Featured image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.46f),
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.46f)
                .background(Color.Black.copy(alpha = 0.45f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    Button(
                        onClick = { navController.navigate("calculate_value") },
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp).height(56.dp),
                        colors = CustomButtonColors()
                    ) {
                        Text("Calculate cost of a player")
                    }
                    Button(
                        onClick = { navController.navigate("featured_players") },
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp).height(56.dp),
                        colors = CustomButtonColors()
                    ) {
                        Text("Featured Players")
                    }
                    Button(
                        onClick = { navController.navigate("compare_players") },
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp).height(56.dp),
                        colors = CustomButtonColors()
                    ) {
                        Text("Compare two players")
                    }
                }
            }
        }
    }
}
