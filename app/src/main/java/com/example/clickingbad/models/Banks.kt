package com.example.clickingbad.models

data class Banks(
    val id: String,
    val label: String,
    val description: String,
    val baseCost: Long,
    val cost: Long,
    val amount: Int,
    val rate: Float,
    val risk: Float,
    val unlockRate: Float,
    val unlockedStatus: Boolean
)
