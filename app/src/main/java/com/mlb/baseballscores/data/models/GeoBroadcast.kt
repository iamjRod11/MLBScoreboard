package com.mlb.baseballscores.data.models

data class GeoBroadcast(
    val type: GeoBroadcastType? = null,
    val market: GeoMarket? = null,
    val media: Media? = null,
    val lang: String = "",
    val region: String = ""
)
