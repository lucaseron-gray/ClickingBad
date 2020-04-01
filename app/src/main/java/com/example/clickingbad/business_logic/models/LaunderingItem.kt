package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_laundering_table")
data class LaunderingItem(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("rps")
	val rps: Double? = null,

	@field:SerializedName("unlock_rps")
	val unlockRps: Float? = null,

	@field:SerializedName("cost")
	val cost: Long? = null,

	@field:SerializedName("base_cost")
	val baseCost: Long? = null,

	@field:SerializedName("unlocked")
	val unlocked: Boolean? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: String
)
