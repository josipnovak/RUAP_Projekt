package hr.ferit.josipnovak.myapplication.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CalculatedValue(
    navController: NavController,
    value: Int
){
    Text("Calculated value is {$value}")
}
