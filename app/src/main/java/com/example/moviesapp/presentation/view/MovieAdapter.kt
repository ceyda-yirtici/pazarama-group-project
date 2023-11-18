package com.example.moviesapp.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieRowBinding

class MovieAdapter (var mContext: Context,
                    var movieList: List<String>)
    :RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

    inner class MovieHolder(var binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieRowBinding.inflate(LayoutInflater.from(mContext), parent,false)
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList[position]
        val movieRow = holder.binding
    }
}