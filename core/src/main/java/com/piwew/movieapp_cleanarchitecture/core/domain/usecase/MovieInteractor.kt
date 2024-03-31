package com.piwew.movieapp_cleanarchitecture.core.domain.usecase

import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}