package com.mlb.baseballscores.ui.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlb.baseballscores.data.models.Scoreboard
import com.mlb.baseballscores.data.repository.ScoreboardRepository
import com.mlb.baseballscores.ui.mvvm.model.ScoreboardUiState
import com.mlb.baseballscores.util.DateUtil
import com.mlb.baseballscores.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository,
    private val dateUtil: DateUtil,
): ViewModel() {

    private val scoreboardState = MutableStateFlow<Scoreboard?>(null)
    private val loadingState = MutableStateFlow(false)
    private val errorState = MutableStateFlow<String?>(null)
    private val refreshState = MutableStateFlow(false)
    private val initialState = ScoreboardUiState(isLoading = false, error = null)
    private var lastRefreshTime: Long = 0
    internal val uiState: StateFlow<ScoreboardUiState> = combine(
        scoreboardState,
        loadingState,
        errorState,
        refreshState,
    ) { scoreboard, isLoading, error, refresh ->
        initialState.copy(
            scoreboardData = scoreboard,
            isLoading = isLoading,
            error = error,
            canRefresh = refresh
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialState)

    internal fun getMlBScoreBoard() {
        viewModelScope.launch {
            val response = scoreboardRepository.getMlbScoreBoard()
            errorState.value = null
            scoreboardState.value = null
            response.collect {
                when (it) {
                    is NetworkResult.Loading -> {
                        loadingState.value = true
                    }
                    is NetworkResult.ScoreBoardSuccess -> {
                        loadingState.value = false
                        scoreboardState.value = it.data
                        lastRefreshTime = System.currentTimeMillis()
                    }
                    is NetworkResult.Error -> {
                        loadingState.value = false
                        errorState.value = it.message
                    }
                }
            }
        }
    }

    internal fun formatDate(date: String): String {
        return dateUtil.formatIsoDateToDisplay(date)
    }

    internal fun canRefreshAgain(lastRefreshTime: Long): Boolean {
        val elapsed = System.currentTimeMillis() - lastRefreshTime
        return elapsed >= 10 * 60 * 1000 // 10 minutes in ms
    }
}