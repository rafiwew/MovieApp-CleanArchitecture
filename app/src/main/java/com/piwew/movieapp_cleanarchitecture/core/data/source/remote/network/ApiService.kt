package com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network

import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.response.ListMovieResponse
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key") apiKey: String
    ): ListMovieResponse
}