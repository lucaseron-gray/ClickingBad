package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_upgrades_table")
data class UpgradesItem(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("action")
	val action: String? = null,

	@field:SerializedName("unlocked")
	val unlocked: Boolean? = null,

	@field:SerializedName("mod")
	val mod: Float? = null,

	@field:SerializedName("cost")
	val cost: Long? = null,

	@field:SerializedName("prereq")
	val prereq: String? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: String
)
