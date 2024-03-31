package com.piwew.movieapp_cleanarchitecture.core.data.source.local

import com.piwew.movieapp_cleanarchitecture.core.data.source.local.entity.MovieEntity
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateFavoriteMovie(movieEntity)
    }
}