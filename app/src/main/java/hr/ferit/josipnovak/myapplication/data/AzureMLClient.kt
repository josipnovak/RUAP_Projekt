import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object AzureMLClient {
    private const val URL = "http://078e441f-025b-4fe6-a818-3c7b054f2812.germanywestcentral.azurecontainer.io/score"
    private const val API_KEY = "1TsDTxLdChSGaxhjgTekbmL3oJGrXuhv"
    private val client = OkHttpClient()

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
        contractExpiresDays: String,
        joinedClubDays: String
    ) {
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
                    put("contract_expires_days", 512)
                    put("joined_club_days", 2773)
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
}
