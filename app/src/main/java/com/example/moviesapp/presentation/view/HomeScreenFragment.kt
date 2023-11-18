package com.example.moviesapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.homeScreenFragmentRV.visibility = View.GONE
            binding.homeScreenError.visibility = View.GONE
            binding.homeScreenProgressBar.visibility = View.VISIBLE
            initView()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.searchView.setOnQueryTextListener (object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.loadData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.loadData(newText)
                }
                return true
            }
        })
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

        viewModel.isLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it){
                    binding.homeScreenProgressBar.visibility = View.VISIBLE
                    binding.homeScreenFragmentRV.visibility = View.GONE
                    binding.homeScreenError.visibility = View.GONE
                } else {
                    binding.homeScreenFragmentRV.visibility = View.VISIBLE
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                binding.homeScreenError.visibility = View.VISIBLE
            }
        }
    }


}