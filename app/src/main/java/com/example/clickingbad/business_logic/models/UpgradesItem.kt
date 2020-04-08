package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_upgrades_table")
data class UpgradesItem(

	@field:SerializedName("label")
	val label: String = "",

	@field:SerializedName("description")
	val description: String = "",

	@field:SerializedName("action")
	val action: String = "",

	@field:SerializedName("unlocked")
	var unlocked: Boolean = false,

	@field:SerializedName("mod")
	val mod: Float = 0f,

	@field:SerializedName("cost")
	val cost: Long = 0,

	@field:SerializedName("prereq")
	val prereq: String = "",

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: String = ""

)
