package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.repository.MoviesRepository
import com.example.moviesapp.util.Resource
import com.example.moviesapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    private val mutableMovieDetail = MutableLiveData<Any?>()
    val movieDetail: LiveData<Any?> get() = mutableMovieDetail

    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    private val mutableError = MutableLiveData<String?>()
    val error: LiveData<String?> get() = mutableError


    fun loadData(movieId: String){
        viewModelScope.launch {
            mutableIsLoading.value = true
            val result = moviesRepository.getMovieDetails(movieId)
            when(result.status){
                Status.SUCCESS -> {
                    mutableIsLoading.value = false
                }
                Status.ERROR -> {
                    mutableError.value = result.message
                    mutableIsLoading.value = false
                }
                Status.LOADING -> {
                    mutableIsLoading.value = true
                }
            }
        }
    }

}

