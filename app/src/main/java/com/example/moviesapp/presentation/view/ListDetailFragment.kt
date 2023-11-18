package com.example.moviesapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.presentation.viewmodel.ListDetailViewModel

class ListDetailFragment: Fragment() {

    private lateinit var viewModel : ListDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListDetailViewModel::class.java]

        arguments?.getString("movieId")?.let { movieId->
            viewModel.loadData(movieId)
        }

        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.isLoading.observe(viewLifecycleOwner){isLoading->

        }

        viewModel.error.observe(viewLifecycleOwner){error->

        }

        viewModel.movieDetail.observe(viewLifecycleOwner){ movieDetail->

        }
    }


}