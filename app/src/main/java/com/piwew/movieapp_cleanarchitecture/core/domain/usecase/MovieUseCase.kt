package com.piwew.movieapp_cleanarchitecture.core.domain.usecase

import androidx.lifecycle.LiveData
import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
    fun getFavoriteMovie(): LiveData<List<Movie>>
    fun setFavoriteTourism(movie: Movie, state: Boolean)
}