package com.mlb.baseballscores.data.models

data class Ticket(
    val summary: String = "",
    val numberAvailable: Int = 0,
    val links: List<LinkSimple> = emptyList()
)

