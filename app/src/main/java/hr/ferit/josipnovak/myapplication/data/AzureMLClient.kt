import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

object AzureMLClient {
    private const val URL = "http://f1292e56-027b-45c5-acbd-728b1b2f081b.germanywestcentral.azurecontainer.io/score"
    private const val API_KEY = "DSPxK44KEHTIWIsXt8JyH6cjMucw2jct"
    private val client = OkHttpClient()

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendRequest(
        callback: (String?) -> Unit,
        age: String,
        height: String,
        nationality: String,
        maxPrice: String,
        position: String,
        shirtNr: String,
        foot: String,
        club: String,
        outfitter: String,
        contractExpires: String,
        joinedClub: String
    ) {
        val contractExpiresDays = convertDateToDays(contractExpires)
        val joinedClubDays = convertDateToDays(joinedClub)

        val jsonBody = JSONObject().apply {
            put("Inputs", JSONObject().apply {
                put("input1", JSONArray().put(JSONObject().apply {
                    put("age", age.toInt())
                    put("height", height.toDouble())
                    put("nationality", nationality)
                    put("max_price", maxPrice.toInt())
                    put("position", position)
                    put("shirt_nr", shirtNr.toInt())
                    put("foot", foot)
                    put("club", club)
                    put("outfitter", outfitter)
                    put("contract_expires_days", contractExpiresDays)
                    put("joined_club_days", joinedClubDays)
                }))
            })
        }
        println(jsonBody.toString())
        val body = RequestBody.create("application/json; charset=utf-8".toMediaType(), jsonBody.toString())
        val request = Request.Builder()
            .url(URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $API_KEY")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                callback(response.body?.string())
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateToDays(date: String): Int {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val parsedDate = LocalDate.parse(date, formatter)

        val currentTime = LocalDate.now()

        return abs(ChronoUnit.DAYS.between(currentTime, parsedDate).toInt())
    }
}
