package com.mlb.baseballscores.data.models

data class Team(
    val id: String = "",
    val uid: String = "",
    val location: String = "",
    val name: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val color: String = "",
    val alternateColor: String = "",
    val isActive: Boolean = false,
    val links: List<LinkDetail> = emptyList(),
    val logo: String = ""
)
