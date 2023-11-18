package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.MovieList
import com.example.moviesapp.domain.model.Search
import com.example.moviesapp.util.Resource
import retrofit2.http.Query

interface MoviesRepository {

    suspend fun getDataWithName(movieName: String = "batman"): Resource<MovieList>

    suspend fun getMovieDetails(@Query("i")  imdbId:String): Resource<MovieDetails>
}