package com.mlb.baseballscores.data.models

data class Link(
    val language: String? = null,
    val rel: List<String> = emptyList(),
    val href: String = "",
    val text: String = "",
    val shortText: String = "",
    val isExternal: Boolean? = false,
    val isPremium: Boolean? = false
)
