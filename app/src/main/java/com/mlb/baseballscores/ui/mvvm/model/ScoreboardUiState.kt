package com.mlb.baseballscores.ui.mvvm.model

import com.mlb.baseballscores.data.models.Scoreboard

data class ScoreboardUiState(
    val scoreboardData: Scoreboard? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val canRefresh: Boolean = true
)
