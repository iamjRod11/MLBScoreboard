package com.mlb.baseballscores.data.models

data class Competitor(
    val id: String = "",
    val uid: String = "",
    val type: String = "",
    val order: Int = 0,
    val homeAway: String = "",
    val team: Team? = null,
    val score: String = "",
    val probables: List<Probable> = emptyList(),
    val statistics: List<Statistic> = emptyList(),
    val hits: Int = 0,
    val errors: Int = 0,
    val records: List<Record> = emptyList(),
    val leaders: List<LeaderGroup> = emptyList()
)
