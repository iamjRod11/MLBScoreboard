package com.mlb.baseballscores.data.models

data class Statistic(
    val name: String,
    val abbreviation: String,
    val displayValue: String,
    val rankDisplayValue: String? = null
)
