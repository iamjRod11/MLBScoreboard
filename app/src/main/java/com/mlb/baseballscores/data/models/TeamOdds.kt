package com.mlb.baseballscores.data.models

data class TeamOdds(
    val favorite: Boolean = false,
    val underdog: Boolean = false,
    val close: CloseOdds? = null,
    val team: Team? = null
)
