package com.piwew.movieapp_cleanarchitecture.core.domain.usecase

import androidx.lifecycle.LiveData
import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie(): LiveData<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteTourism(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}