package com.example.clickingbad.models

data class GameStore(
    val manufacturing: List<Manufacturing>,
    val distribution: List<Distribution>,
    val banks: List<Banks>,
    val upgrades: List<Upgrades>
)
