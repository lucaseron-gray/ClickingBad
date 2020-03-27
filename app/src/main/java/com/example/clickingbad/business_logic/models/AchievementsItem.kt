package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_achievements_table")
data class AchievementsItem(

    @field:SerializedName("label")
    val label: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("property")
    val property: String? = null,
    /*
    * Available properties:
    *
    * batches_cooked
    * batches_cooked_hand
    * batches_sold
    * batches_sold_hand
    * upgrades_purchased
    * cash_earned
    * cash_spent
    * seconds_playing
    *
    * */

    @field:SerializedName("required")
    val required: Long? = null, // based on field 'property', data from "cb_player_stats"

    @field:SerializedName("unlocked")
    val unlocked: Boolean = false,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: Int? = null

)