package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_player_data_table")
data class PlayerData(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("batch_amount")
    val batchAmount: Long = 0,

    @field:SerializedName("batch_rps_net")
    val batchRpsNet: Long = 0,

    @field:SerializedName("batch_rps_gross")
    val batchRpsGross: Long = 0,

    @field:SerializedName("batch_price")
    val batchPrice: Double = 0.0, // widget_roi

    @field:SerializedName("cash_amount")
    val cashAmount: Long = 0,

    @field:SerializedName("cash_laundered")
    val cashLaundered: Long = 0,

    @field:SerializedName("cash_rps")
    val cashRps: Long = 0

)