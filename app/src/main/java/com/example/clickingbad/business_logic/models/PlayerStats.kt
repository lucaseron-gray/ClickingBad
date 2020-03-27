package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_player_stats")
data class PlayerStats(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("batches_cooked")
    val batchesCooked: Long = 0,

    @field:SerializedName("batches_cooked_hand")
    val batchesCookedHand: Long = 0,

    @field:SerializedName("batches_sold")
    val batchesSold: Long = 0,

    @field:SerializedName("batches_sold_hand")
    val batchesSoldHand: Long = 0,

    @field:SerializedName("upgrades_purchased")
    val upgradesPurchased: Int = 0,

    @field:SerializedName("cash_earned")
    val cashEarned: Long = 0,

    @field:SerializedName("cash_spent")
    val cashSpent: Long = 0,

    @field:SerializedName("seconds_playing")
    val secondsPlaying: Int = 0

)