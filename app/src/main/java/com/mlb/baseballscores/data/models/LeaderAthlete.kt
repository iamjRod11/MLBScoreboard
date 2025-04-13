package com.mlb.baseballscores.data.models

data class LeaderAthlete(
    val id: String = "",
    val fullName: String = "",
    val displayName: String = "",
    val shortName: String = "",
    val links: List<LinkSimple> = emptyList(),
    val headshot: String = "",
    val jersey: String = "",
    val position: Position? = null,
    val team: TeamId? = null,
    val active: Boolean = false
)
