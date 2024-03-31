package com.piwew.movieapp_cleanarchitecture.core.domain.usecase

import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}