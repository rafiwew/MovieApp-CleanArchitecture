package com.piwew.movieapp_cleanarchitecture.core.di

import android.content.Context
import com.piwew.movieapp_cleanarchitecture.core.data.MovieRepository
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.LocalDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.room.MovieDatabase
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.RemoteDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network.ApiConfig
import com.piwew.movieapp_cleanarchitecture.core.domain.repository.IMovieRepository
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieInteractor
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieUseCase
import com.piwew.movieapp_cleanarchitecture.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}