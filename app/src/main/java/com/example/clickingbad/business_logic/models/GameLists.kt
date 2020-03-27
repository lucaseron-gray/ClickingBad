package com.example.clickingbad.business_logic.models

import com.google.gson.annotations.SerializedName

data class GameLists(

    @field:SerializedName("manufacturing")
	val manufacturing: List<ManufacturingItem>? = null,

    @field:SerializedName("distribution")
	val distribution: List<DistributionItem>? = null,

    @field:SerializedName("laundering")
	val laundering: List<LaunderingItem>? = null,

    @field:SerializedName("upgrades")
	val upgrades: List<UpgradesItem>? = null,

    @field:SerializedName("achievements")
    val achievements: List<AchievementsItem>? = null,

    @field:SerializedName("events")
    val events: List<EventsItem>? = null,

    @field:SerializedName("player_data")
    val playerData: List<PlayerData>? = null,

    @field:SerializedName("player_stats")
    val playerStats: List<PlayerStats>? = null

)