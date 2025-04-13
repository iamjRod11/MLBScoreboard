package com.mlb.baseballscores.data.models

data class LeagueSeason(
    val year: Int = 0,
    val startDate: String = "",
    val endDate: String = "",
    val displayName: String = "",
    val type: SeasonType? = null
)
