package com.example.clickingbad.models

data class GameStore(
    val manufacturing: List<Manufacturing>,
    val distribution: List<Distribution>,
    val laundering: List<Laundering>,
    val upgrades: List<Upgrades>
)
