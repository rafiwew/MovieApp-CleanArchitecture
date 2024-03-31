package com.piwew.movieapp_cleanarchitecture.core.data.source.local

import androidx.lifecycle.LiveData
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.entity.MovieEntity
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {
    fun getAllMovie(): LiveData<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateFavoriteMovie(movieEntity)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }
}