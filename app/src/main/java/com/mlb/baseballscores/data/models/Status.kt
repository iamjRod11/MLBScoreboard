package com.mlb.baseballscores.data.models

data class Status(
    val clock: Int = 0,
    val displayClock: String = "",
    val period: Int = 0,
    val type: StatusType? = null
)
