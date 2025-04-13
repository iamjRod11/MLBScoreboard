package com.mlb.baseballscores.data.models

data class Scoreboard(
    val leagues: List<League> = emptyList(),
    val season: Season? = null,
    val day: Day? = null,
    val events: List<Event> = emptyList()
)
