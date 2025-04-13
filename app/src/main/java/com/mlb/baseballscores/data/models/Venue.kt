package com.mlb.baseballscores.data.models

data class Venue(
    val id: String = "",
    val fullName: String = "",
    val address: Address? = null,
    val indoor: Boolean = false
)
