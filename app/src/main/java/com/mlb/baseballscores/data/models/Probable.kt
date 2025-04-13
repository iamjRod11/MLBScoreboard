package com.mlb.baseballscores.data.models

data class Probable(
    val name: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val abbreviation: String = "",
    val playerId: Int = 0,
    val athlete: Athlete? = null,
    val statistics: List<Statistic> = emptyList(),
    val record: String? = null
)
