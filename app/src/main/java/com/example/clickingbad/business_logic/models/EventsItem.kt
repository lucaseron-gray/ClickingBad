package com.example.clickingbad.business_logic.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cb_events_table")
data class EventsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("chance")
    val chance: Double? = null,

    @field:SerializedName("mod")
    val mod: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")
    val id: String

)