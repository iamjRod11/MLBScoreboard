package com.mlb.baseballscores.util

sealed class NetworkResult<out T> {
    data class ScoreBoardSuccess<out T>(val data: T): NetworkResult<T>()
    data class Loading<T>(val isLoading: Boolean): NetworkResult<T>()
    data class Error<T>(val message: String): NetworkResult<T>()
}
