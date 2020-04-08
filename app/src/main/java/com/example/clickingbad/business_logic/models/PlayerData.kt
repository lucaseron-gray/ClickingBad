package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "cb_player_data_table")
data class PlayerData(

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("batch_amount")
    var batchAmount: Long = 0,

    @field:SerializedName("batch_rps_net")
    var batchRpsNet: Long = 0,

    @field:SerializedName("batch_rps_gross")
    var batchRpsGross: Long = 0,

    @field:SerializedName("batch_price")
    var batchPrice: Int = 1,

    @field:SerializedName("cash_amount")
    var cashAmount: Long = 0,

    @field:SerializedName("cash_laundered")
    var cashLaundered: Long = 0,

    @field:SerializedName("cash_rps")
    var cashRps: Long = 0,

    var lastTick: Long = 0

)
