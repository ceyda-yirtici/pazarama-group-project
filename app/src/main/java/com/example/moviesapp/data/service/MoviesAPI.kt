package com.example.moviesapp.data.service

import com.example.moviesapp.domain.model.MovieList
import com.example.moviesapp.domain.model.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET()
    suspend fun getDataWithName(movieName: String = "batman"): Response<MovieList>

    @GET()
    suspend fun getMovieDetails(@Query("i")  imdbId:String): Response<Search>
}