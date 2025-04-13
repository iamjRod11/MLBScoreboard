package com.mlb.baseballscores.data.models

data class Odds(
    val provider: Provider? = null,
    val details: String = "",
    val overUnder: Double = 0.0,
    val spread: Double = 0.0,
    val awayTeamOdds: TeamOdds? = null,
    val homeTeamOdds: TeamOdds? = null,
    val open: OddsValue? = null,
    val current: OddsValue? = null
)
