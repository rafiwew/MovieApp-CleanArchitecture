package com.piwew.movieapp_cleanarchitecture.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}