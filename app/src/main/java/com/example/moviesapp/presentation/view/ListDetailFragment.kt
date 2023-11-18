package com.example.moviesapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.databinding.ListDetailScreenBinding
import com.example.moviesapp.presentation.viewmodel.ListDetailViewModel
import com.example.moviesapp.util.downloadUrl

class ListDetailFragment: Fragment() {

    private lateinit var viewModel : ListDetailViewModel

    private var _binding: ListDetailScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

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
            if(movieDetail == null) return@observe
            //binding.detailMovie.downloadUrl(movieDetail.Poster)
            binding.detailTitle.text = movieDetail.Title
            binding.detailYear.text = movieDetail.Year
            binding.detailActors.text = movieDetail.Actors
            binding.detailCountries.text = movieDetail.Country
            binding.detailDirector.text = "Director: ${movieDetail.Director}"
            binding.detailRatings.text = "IMDB Rating: ${movieDetail.imdbRating}"
        }
    }


}