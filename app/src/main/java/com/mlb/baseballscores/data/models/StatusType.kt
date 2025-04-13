package com.mlb.baseballscores.data.models

data class StatusType(
    val id: String = "",
    val name: String = "",
    val state: String = "",
    val completed: Boolean = false,
    val description: String = "",
    val detail: String = "",
    val shortDetail: String = ""
)
