package com.example.clickingbad.models

data class Manufacturing(
    val id: String,
    val label: String,
    val description: String,
    val baseCost: Long,
    val cost: Long,
    val amount: Int,
    val rps: Double,
    val risk: Float,
    val unlockRate: Float,
    val unlockedStatus: Boolean,

    val expanded: Boolean?
)
