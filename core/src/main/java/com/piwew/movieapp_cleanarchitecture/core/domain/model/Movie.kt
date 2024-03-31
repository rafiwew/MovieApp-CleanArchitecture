package com.piwew.movieapp_cleanarchitecture.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val originalLanguage: String,
    val posterPath: String,
    val backdropPath: String,
    val isFavorite: Boolean
) : Parcelable