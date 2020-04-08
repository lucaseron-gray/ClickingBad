package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_player_stats_table")
data class PlayerStats(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("batches_cooked")
    var batchesCooked: Long = 0,

    @field:SerializedName("batches_cooked_hand")
    var batchesCookedHand: Long = 0,

    @field:SerializedName("batches_sold")
    var batchesSold: Long = 0,

    @field:SerializedName("batches_sold_hand")
    var batchesSoldHand: Long = 0,

    @field:SerializedName("upgrades_purchased")
    var upgradesPurchased: Int = 0,

    @field:SerializedName("cash_earned")
    var cashEarned: Long = 0,

    @field:SerializedName("cash_spent")
    var cashSpent: Long = 0,

    @field:SerializedName("seconds_playing")
    var secondsPlaying: Int = 0

)
