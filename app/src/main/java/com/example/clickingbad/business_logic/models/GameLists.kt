package com.example.clickingbad.business_logic.models

import com.google.gson.annotations.SerializedName

data class GameLists(

    @field:SerializedName("manufacturing")
	val manufacturing: List<ManufacturingItem> = emptyList(),

    @field:SerializedName("distribution")
	val distribution: List<DistributionItem> = emptyList(),

    @field:SerializedName("laundering")
	val laundering: List<LaunderingItem> = emptyList(),

    @field:SerializedName("upgrades")
	val upgrades: List<UpgradesItem> = emptyList(),

    @field:SerializedName("achievements")
    val achievements: List<AchievementsItem> = emptyList(),

    @field:SerializedName("events")
    val events: List<EventsItem> = emptyList()

)
