package com.mlb.baseballscores.data.models

data class Event(
    val id: String = "",
    val uid: String = "",
    val date: String = "",
    val name: String = "",
    val shortName: String = "",
    val season: EventSeason? = null,
    val competitions: List<Competition> = emptyList(),
    val links: List<Link> = emptyList(),
    val weather: Weather? = null,
    val status: Status? = null
)
