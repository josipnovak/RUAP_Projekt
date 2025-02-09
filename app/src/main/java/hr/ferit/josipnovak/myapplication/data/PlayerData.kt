package hr.ferit.josipnovak.myapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class PlayerData(
    val id: Int = 0,
    val name: String = "",
    val age: String = "",
    val height: String = "",
    val nationality: String = "",
    val maxPrice: String = "",
    val position: String = "",
    val shirtNr: String = "",
    val foot: String = "",
    val league: String = "",
    val club: String = "",
    val outfitter: String = "",
    val contractExpiresDays: String = "",
    val joinedClubDays: String = "",
    val calculatedValue: Int = 0
) : Parcelable