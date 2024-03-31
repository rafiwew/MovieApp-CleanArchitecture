package com.piwew.movieapp_cleanarchitecture.core.data.source.remote

import android.util.Log
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network.ApiResponse
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network.ApiService
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviePopular("f0268cabde6dd54ecef7a8bed4674e47")
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}