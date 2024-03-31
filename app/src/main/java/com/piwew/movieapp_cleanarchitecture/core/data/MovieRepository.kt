package com.piwew.movieapp_cleanarchitecture.core.data

import com.piwew.movieapp_cleanarchitecture.core.data.source.local.LocalDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.RemoteDataSource
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.network.ApiResponse
import com.piwew.movieapp_cleanarchitecture.core.data.source.remote.response.MovieResponse
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.domain.repository.IMovieRepository
import com.piwew.movieapp_cleanarchitecture.core.utils.AppExecutors
import com.piwew.movieapp_cleanarchitecture.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}