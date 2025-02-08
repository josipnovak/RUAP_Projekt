package hr.ferit.josipnovak.myapplication.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

class DatabaseModel : ViewModel() {
    val database = Firebase.database
    val myRef = database.getReference("players")

    fun addNewPlayer(playerData: PlayerData) {
        val playerDataMap: Map<String, Any?> = mapOf(
            "name" to playerData.name,
            "age" to playerData.age,
            "height" to playerData.height,
            "nationality" to playerData.nationality,
            "maxPrice" to playerData.maxPrice,
            "position" to playerData.position,
            "shirtNr" to playerData.shirtNr,
            "foot" to playerData.foot,
            "club" to playerData.club,
            "outfitter" to playerData.outfitter,
            "contractExpiresDays" to playerData.contractExpiresDays,
            "joinedClubDays" to playerData.joinedClubDays,
            "calculatedValue" to playerData.calculatedValue
        )

        val sanitizedMap = playerDataMap.mapKeys { entry ->
            entry.key.replace(Regex("[.\\$/\\[\\]#]"), "_")
        }

        val randomPlayerId = (1..9999).random()

        val playerRef = myRef.child(randomPlayerId.toString()).child("data")

        playerRef.setValue(sanitizedMap)
    }

    fun getAllPlayers(onSuccess: (List<PlayerData>) -> Unit, onFailure: (String) -> Unit) {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val playersList = mutableListOf<PlayerData>()
                if (snapshot.exists()) {
                    for (playerSnapshot in snapshot.children) {
                        val playerData = playerSnapshot.child("data").getValue(PlayerData::class.java)
                        if (playerData != null) {
                            playersList.add(playerData)
                        }
                    }
                    onSuccess(playersList)
                } else {
                    onFailure("No players found.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure("Failed to read value: ${error.message}")
            }
        })
    }
}