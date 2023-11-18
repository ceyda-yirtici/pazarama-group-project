package com.example.moviesapp.data.repository

import com.example.moviesapp.data.service.MoviesAPI
import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.MovieList
import com.example.moviesapp.domain.model.Search
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor (val api: MoviesAPI) : MoviesRepository  {

    override suspend fun getDataWithName(movieName: String): Resource<MovieList> {
        return try {
            val response = api.getDataWithName(movieName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Body boş geldi!",null)
            } else {
                Resource.error("Response successful değil!",null)
            }
        } catch (e: Exception) {
            Resource.error("Bütün olay patladı!",null)
        }
    }

    override suspend fun getMovieDetails(imdbId: String): Resource<MovieDetails> {
        return try {
            val response = api.getMovieDetails(imdbId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Body boş geldi!",null)
            } else {
                Resource.error("Response successful değil!",null)
            }
        } catch (e: Exception) {
            Resource.error("Bütün olay patladı!",null)
        }
    }
}