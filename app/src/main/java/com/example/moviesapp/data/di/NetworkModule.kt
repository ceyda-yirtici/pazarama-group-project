package com.example.moviesapp.data.di

import com.example.moviesapp.data.repository.MoviesRepositoryImpl
import com.example.moviesapp.data.service.MoviesAPI
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun injectRetrofitAPI(): MoviesAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MoviesAPI::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideMoviesRepository(
        apiService: MoviesAPI,
    ): MoviesRepository = MoviesRepositoryImpl(apiService)


}