package com.mlb.baseballscores.data.models

data class LeaderGroup(
    val name: String,
    val displayName: String,
    val shortDisplayName: String,
    val abbreviation: String,
    val leaders: List<Leader>
)
