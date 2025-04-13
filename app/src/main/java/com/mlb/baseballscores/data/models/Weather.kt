package com.mlb.baseballscores.data.models

data class Weather(
    val displayValue: String = "",
    val temperature: Int = 0,
    val highTemperature: Int = 0,
    val conditionId: String = "",
    val link: Link? = null
)
