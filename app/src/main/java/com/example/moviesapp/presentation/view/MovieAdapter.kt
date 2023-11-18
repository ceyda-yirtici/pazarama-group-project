package com.example.moviesapp.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.moviesapp.databinding.MovieRowBinding
import com.example.moviesapp.domain.model.Search
import com.example.moviesapp.util.downloadUrl

class MovieAdapter
    : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(var binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root)

    private var movieList: List<Search> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateMovieList(item: List<Search>) {
        item.let {
            movieList = item
        }
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return movieList.size

    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList[position]
        val movieRow = holder.binding

        movieRow.textViewTitle.text = movie.Title
        movieRow.textViewYear.text = movie.Year

        movieRow.cardViewMovie.setOnClickListener {
            val goTo = HomeScreenFragmentDirections.homeToDetail(movieId = movie.imdbID)
            Navigation.findNavController(it).navigate(goTo)
        }

        val progressDrawable = createProgressDrawable(movieRow.imageViewMovie.context)

        movieRow.imageViewMovie.downloadUrl(movie.Poster,progressDrawable)

    }

    private fun createProgressDrawable(context: Context): CircularProgressDrawable {
        val progressDrawable = CircularProgressDrawable(context)
        progressDrawable.strokeWidth = 10f
        progressDrawable.centerRadius = 50f
        progressDrawable.start()
        return progressDrawable
    }

}