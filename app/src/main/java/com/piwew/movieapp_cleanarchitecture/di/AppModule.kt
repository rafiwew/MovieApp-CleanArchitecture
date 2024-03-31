package com.piwew.movieapp_cleanarchitecture.di

import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieInteractor
import com.piwew.movieapp_cleanarchitecture.core.domain.usecase.MovieUseCase
import com.piwew.movieapp_cleanarchitecture.detail.MovieDetailViewModel
import com.piwew.movieapp_cleanarchitecture.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}