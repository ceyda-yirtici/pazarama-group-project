package com.example.moviesapp.data.service

import com.example.moviesapp.domain.model.MovieDetails
import com.example.moviesapp.domain.model.MovieList
import com.example.moviesapp.domain.model.Search
import com.example.moviesapp.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET(".")
    suspend fun getDataWithName(
        @Query("s") searchString :String,
        @Query("apikey") apiKey :String = API_KEY
    ): Response<MovieList>


    @GET(".")
    suspend fun getMovieDetails(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey: String = API_KEY
    ): Response<MovieDetails>
}