package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_laundering_table")
data class LaunderingItem(

	@field:SerializedName("amount")
	var amount: Int = 0,

	@field:SerializedName("label")
	val label: String = "",

	@field:SerializedName("description")
	val description: String = "",

	@field:SerializedName("rps")
	val rps: Long = 0,

	@field:SerializedName("unlock_rps")
	val unlockRps: Float = 0f,

	@field:SerializedName("cost")
	var cost: Long = 0,

	@field:SerializedName("base_cost")
	val baseCost: Long = 0,

	@field:SerializedName("unlocked")
	var unlocked: Boolean = false,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: String = ""

)
