package com.mlb.baseballscores.data.repository

import com.mlb.baseballscores.data.models.Scoreboard
import com.mlb.baseballscores.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ScoreboardRepository {
    suspend fun getMlbScoreBoard(): Flow<NetworkResult<Scoreboard>>
}
