package com.mlb.baseballscores.data.models

data class OddsDetail(
    val value: Double = 0.0,
    val displayValue: String = "",
    val alternateDisplayValue: String = "",
    val decimal: Double = 0.0,
    val fraction: String = "",
    val american: String = ""
)
