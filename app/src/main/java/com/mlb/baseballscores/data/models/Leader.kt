package com.mlb.baseballscores.data.models

data class Leader(
    val displayValue: String = "",
    val value: Double = 0.0,
    val athlete: LeaderAthlete? = null,
    val team: TeamId? = null
)
