package com.mlb.baseballscores.di

import com.mlb.baseballscores.data.repository.ScoreboardRepository
import com.mlb.baseballscores.data.repository.ScoreboardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesScoreboardRepository(impl: ScoreboardRepositoryImpl): ScoreboardRepository = impl
}
