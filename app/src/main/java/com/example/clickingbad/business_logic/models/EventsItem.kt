package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_events_table")
data class EventsItem(

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("chance")
    val chance: Double = 0.0,

    @field:SerializedName("mod")
    val mod: Int = 0,

    @field:SerializedName("type")
    val type: String = "",

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String = ""

)
