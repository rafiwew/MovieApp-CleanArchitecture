package com.piwew.movieapp_cleanarchitecture.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.piwew.movieapp_cleanarchitecture.core.data.source.local.LocalDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.RemoteDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network.ApiResponse
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.response.MovieResponse
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.domain.repository.IMovieRepository
import com.piwew.movieapp_cleanarchitecture.core.utils.AppExecutors
import com.piwew.movieapp_cleanarchitecture.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMovieRepository {

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localDataSource.getAllMovie().switchMap { data ->
                    MutableLiveData(DataMapper.mapEntitiesToDomain(data))
                }
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()

    override fun getFavoriteMovie(): LiveData<List<Movie>> {
        return localDataSource.getFavoriteMovie().switchMap { data ->
            MutableLiveData(DataMapper.mapEntitiesToDomain(data))
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors,
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
}