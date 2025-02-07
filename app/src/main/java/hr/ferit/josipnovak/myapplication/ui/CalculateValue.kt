import android.icu.util.Calendar
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.josipnovak.myapplication.R
import hr.ferit.josipnovak.myapplication.ui.theme.CustomButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.interaction.MutableInteractionSource
import hr.ferit.josipnovak.myapplication.data.leagues
import hr.ferit.josipnovak.myapplication.data.clubs_premier_league
import hr.ferit.josipnovak.myapplication.data.clubs_bundesliga
import hr.ferit.josipnovak.myapplication.data.clubs_la_liga
import hr.ferit.josipnovak.myapplication.data.clubs_ligue_1
import hr.ferit.josipnovak.myapplication.data.clubs_serie_a
import hr.ferit.josipnovak.myapplication.data.nationalites
import hr.ferit.josipnovak.myapplication.data.outfitters
import hr.ferit.josipnovak.myapplication.data.positions
import org.json.JSONObject
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalculateValue(
    navController: NavController
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .padding(bottom = 20.dp)
                .background(Color.Black.copy(alpha = 0.60f)),
        ) {

            Text(
                text = "Input information about the player",
                fontSize = 19.sp,
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                color = Color.White
            )
            PlayerInputForm(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerInputForm(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var shirtNum by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }
    var nationality by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var league by remember { mutableStateOf("") }
    var club by remember { mutableStateOf("") }
    var outfitter by remember { mutableStateOf("") }
    var contractExpire by remember { mutableStateOf("") }
    var contractJoined by remember { mutableStateOf("") }
    var foot by remember { mutableStateOf("right") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            contractExpire = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    var expandedNationality by remember { mutableStateOf(false) }
    var explandedPosition by remember { mutableStateOf(false) }
    var expandedLeague by remember { mutableStateOf(false) }
    var expandedClub by remember { mutableStateOf(false) }
    var expandedOutfitters by remember { mutableStateOf(false) }

    val clubs = when (league) {
        "EPL" -> clubs_premier_league
        "LaLiga" -> clubs_la_liga
        "SerieA" -> clubs_serie_a
        "Bundesliga" -> clubs_bundesliga
        "Ligue1" -> clubs_ligue_1
        else -> emptyList()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black.copy(alpha = 0.1f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(color = Color.White),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = shirtNum,
                    onValueChange = { shirtNum = it },
                    label = { Text("Shirt number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(color = Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = maxPrice,
                    onValueChange = { maxPrice = it },
                    label = { Text("Max Price") },
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(color = Color.White),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )
            }
        }

        item {
            Box {
                Button(
                    onClick = { expandedNationality = true },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    colors = CustomButtonColors(),

                    ) {
                    Text(
                        text = if (nationality.isEmpty()) "Select Nationality" else nationality,
                        color = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expandedNationality,
                    onDismissRequest = { expandedNationality = false }
                ) {
                    nationalites.forEach { nation ->
                        DropdownMenuItem(
                            text = { Text(nation) },
                            onClick = {
                                nationality = nation
                                expandedNationality = false
                            }
                        )
                    }
                }
            }
        }


        item {
            Box {
                Button(
                    onClick = { explandedPosition = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CustomButtonColors(),

                    ) {
                    Text(
                        text = if (position.isEmpty()) "Select Position" else position,
                        color = Color.White
                    )
                }
                DropdownMenu(
                    expanded = explandedPosition,
                    onDismissRequest = { explandedPosition = false }
                ) {
                    positions.forEach { pos ->
                        DropdownMenuItem(
                            text = { Text(pos) },
                            onClick = {
                                position = pos
                                explandedPosition = false
                            }
                        )
                    }
                }
            }
        }

        item {
            Text(
                "Foot",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        foot = "left"
                        println("Foot: $foot")
                    },
                    colors = ButtonDefaults.buttonColors(if (foot == "left") Color.Gray else Color.LightGray)
                ) {
                    Text("Left")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        foot = "right"
                        println("Foot: $foot")
                    },
                    colors = ButtonDefaults.buttonColors(if (foot == "right") Color.Gray else Color.LightGray)
                ) {
                    Text("Right")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        foot = "both"
                        println("Foot: $foot")
                    },
                    colors = ButtonDefaults.buttonColors(if (foot == "both") Color.Gray else Color.LightGray)
                ) {
                    Text("Both")
                }
            }
        }

        item {
            Box {
                Button(
                    onClick = { expandedLeague = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CustomButtonColors(),

                    ) {
                    Text(
                        text = if (league.isEmpty()) "Select League" else league,
                        color = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expandedLeague,
                    onDismissRequest = { expandedLeague = false }
                ) {
                    leagues.forEach { leagueItem ->
                        DropdownMenuItem(
                            text = { Text(leagueItem) },
                            onClick = {
                                league = leagueItem
                                club = ""
                                expandedLeague = false
                            }
                        )
                    }
                }
            }
        }

        if (league.isNotEmpty()) {
            item {
                Box {
                    Button(
                        onClick = { expandedClub = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CustomButtonColors(),

                        ) {
                        Text(
                            text = if (club.isEmpty()) "Select Club" else club,
                            color = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = expandedClub,
                        onDismissRequest = { expandedClub = false }
                    ) {
                        clubs.forEach { clubItem ->
                            DropdownMenuItem(
                                text = { Text(clubItem) },
                                onClick = {
                                    club = clubItem
                                    expandedClub = false
                                }
                            )
                        }
                    }
                }
            }
        } else {
            item {
                Box {
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CustomButtonColors(),
                    ) {
                        Text(
                            text = "Select League First",
                            color = Color.White
                        )
                    }
                }
            }
        }

        item {
            Box {
                Button(
                    onClick = { expandedOutfitters = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CustomButtonColors(),

                    ) {
                    Text(
                        text = if (outfitter.isEmpty()) "Select Outfitter" else outfitter,
                        color = Color.White
                    )
                }
                DropdownMenu(
                    expanded = expandedOutfitters,
                    onDismissRequest = { expandedOutfitters = false }
                ) {
                    outfitters.forEach { outfit ->
                        DropdownMenuItem(
                            text = { Text(outfit) },
                            onClick = {
                                outfitter = outfit
                                expandedOutfitters = false
                            }
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = contractExpire,
                onValueChange = { contractExpire = it },
                label = { Text("Contract Expire Date") },
                modifier = Modifier.fillMaxWidth().clickable {
                    datePickerDialog.show()
                },
                textStyle = TextStyle(color = Color.White),
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
        }

        item {
            OutlinedTextField(
                value = contractJoined,
                onValueChange = { contractJoined = it },
                label = { Text("Contract Joined Date") },
                modifier = Modifier.fillMaxWidth().clickable{
                    datePickerDialog.show()
                },
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
        }

        item {
            Button(
                onClick = {
                    AzureMLClient.sendRequest(
                    callback = { response ->
                        response?.let {
                            val jsonResponse = JSONObject(it)
                            val results = jsonResponse.getJSONObject("Results")
                            val webServiceOutput = results.getJSONArray("WebServiceOutput0")
                            val prediction = webServiceOutput.getJSONObject(0).getDouble("PricePrediction").toInt()
                            Handler(Looper.getMainLooper()).post {
                                navController.navigate("calculated_value/$prediction")
                            }
                        } ?: run {
                            println("Failed to get a response")
                        }
                    },
                    age = age,
                    height = height.replace(",", "."),
                    nationality = nationality,
                    maxPrice = maxPrice,
                    position = position,
                    shirtNr = shirtNum,
                    foot = foot,
                    club = club,
                    outfitter = outfitter,
                    contractExpiresDays = contractExpire,
                    joinedClubDays = contractJoined
                )},
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp)
                    .fillMaxWidth(0.8f),
                colors = CustomButtonColors()
            ) {
                Text("Calculate cost", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
