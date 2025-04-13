package com.mlb.baseballscores.data.models

data class OddsValue(
    val over: OddsDetail? = null,
    val under: OddsDetail? = null,
    val total: OddsDetail? = null
)
