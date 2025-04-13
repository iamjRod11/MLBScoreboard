package com.mlb.baseballscores.data.models

data class LinkDetail(
    val rel: List<String> = emptyList(),
    val href: String = "",
    val text: String = "",
    val isExternal: Boolean = false,
    val isPremium: Boolean = false,
)
