package com.mlb.baseballscores.data.models

data class League(
    val id: String = "",
    val uid: String = "",
    val name: String = "",
    val abbreviation: String = "",
    val midsizeName: String = "",
    val slug: String = "",
    val season:LeagueSeason? = null,
    val logos: List<Logo> = emptyList(),
    val calendarType: String = "",
    val calendarIsWhitelist: Boolean = false,
    val calendarStartDate: String? = null,
    val calendarEndDate: String? = null,
    val calendar: List<String> = emptyList()
)

