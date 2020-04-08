package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_achievements_table")
data class AchievementsItem(

    @field:SerializedName("label")
    val label: String = "",

    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("property")
    val property: String = "",

    @field:SerializedName("required")
    val required: Long = 0, // based on field 'property', data from "cb_player_stats"

    @field:SerializedName("unlocked")
    var unlocked: Boolean = false,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String = ""

)

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
