package com.mlb.baseballscores.data.repository

import com.mlb.baseballscores.data.api.EspnApi
import com.mlb.baseballscores.util.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ScoreboardRepositoryImpl @Inject constructor(
    private val espnApi: EspnApi
): ScoreboardRepository {

    override suspend fun getMlbScoreBoard() = flow {
        emit(NetworkResult.Loading(isLoading = true))
        val response = espnApi.getMlbScoreBoard()
        emit(NetworkResult.ScoreBoardSuccess(response))
    }.catch { e ->
        emit(NetworkResult.Error(e.message ?: "Unknown Error"))
    }
}
