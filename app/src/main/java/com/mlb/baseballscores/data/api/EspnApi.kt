package com.mlb.baseballscores.data.api

import com.mlb.baseballscores.data.models.Scoreboard
import retrofit2.http.GET
import retrofit2.http.Query

interface EspnApi {

    @GET("v2/sports/baseball/mlb/scoreboard")
    suspend fun getMlbScoreBoard(
        @Query("dates") date: Int? = null
    ): Scoreboard
}