package com.example.clickingbad.models

data class Upgrades(
    val id: String,
    val label: String,
    val description: String,
    val purchased: Boolean,
    val mod: Float,
    val cost: Long,
    val prereq: String
)
