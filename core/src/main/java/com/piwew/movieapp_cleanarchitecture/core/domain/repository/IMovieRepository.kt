package com.piwew.movieapp_cleanarchitecture.core.domain.repository

import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}