package com.mlb.baseballscores.data.models

data class Logo(
    val href: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val alt: String = "",
    val rel: List<String> = emptyList(),
    val lastUpdated: String = ""
)
