package com.mlb.baseballscores.data.models

data class SeasonType(
    val id: String = "",
    val type: Int = 0, // present in LeagueSeason.type
    val name: String = "",
    val abbreviation: String = ""
)
