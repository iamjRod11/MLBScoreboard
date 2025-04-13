package com.mlb.baseballscores.data.models

data class Competition(
    val id: String = "",
    val uid: String = "",
    val date: String = "",
    val attendance: Int = 0,
    val type: SimpleType? = null,
    val timeValid: Boolean = false,
    val neutralSite: Boolean = false,
    val conferenceCompetition: Boolean = false,
    val playByPlayAvailable: Boolean = false,
    val recent: Boolean = false,
    val wasSuspended: Boolean = false,
    val venue: Venue? = null,
    val competitors: List<Competitor> = emptyList(),
    val notes: List<String> = emptyList(),
    val status: Status? = null,
    val broadcasts: List<Broadcast> = emptyList(),
    val format: Format? = null,
    val tickets: List<Ticket> = emptyList(),
    val startDate: String = "",
    val broadcast: String = "",
    val geoBroadcasts: List<GeoBroadcast> = emptyList(),
    val odds: List<Odds> = emptyList(),
    val highlights: List<Highlight> = emptyList(),
    val headlines: List<Headline> = emptyList()
)
