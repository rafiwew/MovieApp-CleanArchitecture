package com.piwew.movieapp_cleanarchitecture.favorite

import androidx.lifecycle.ViewModel
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie()
}