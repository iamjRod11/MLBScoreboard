package com.mlb.baseballscores.data.models

data class Athlete(
    val id: String = "",
    val fullName: String = "",
    val displayName: String = "",
    val shortName: String = "",
    val links: List<LinkSimple?> = emptyList(),
    val headshot: String = "",
    val jersey: String = "",
    val position: String = "",  // For athletes in this context, position is given as a simple string.
    val team: TeamId? = null
)

