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
import hr.ferit.josipnovak.myapplication.Paths
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.ui.theme.CustomButtonColors

@Composable
fun LandingScreen(
    navController: NavController
) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.bg_1),
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
                .background(Color.Black.copy(alpha = 0.25f))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate(Paths.HOME_SCREEN) },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 70.dp).height(56.dp),
                colors = CustomButtonColors()
            ) {
                Text("Start calculating")
            }
        }
    }
}

