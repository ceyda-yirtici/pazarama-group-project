package com.example.moviesapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.FragmentHomeScreenBinding
import com.example.moviesapp.presentation.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {


    private var movieAdapter: MovieAdapter = MovieAdapter()
    private val viewModel: HomeScreenViewModel by viewModels(ownerProducer = { this })
    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }



    private fun initView() {

        val layoutManager = LinearLayoutManager(requireContext())
        binding.homeScreenFragmentRV.layoutManager = layoutManager
        binding.homeScreenFragmentRV.adapter = movieAdapter


    }


    private fun initListener() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            movieAdapter.updateMovieList(it.Search)
        }
    }


}