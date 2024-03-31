package com.piwew.movieapp_cleanarchitecture.detail

import androidx.lifecycle.ViewModel
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}