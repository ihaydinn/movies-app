package com.ismailhakkiaydin.movies.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ismailhakkiaydin.movies.databinding.ItemMovieBinding
import com.ismailhakkiaydin.movies.model.movie.MovieResult

class MovieAdapter : ListAdapter<MovieResult, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
                val itemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
                return ViewHolder(itemMovieBinding)
            }
        }

        fun bind(movieResult: MovieResult) {
            binding.movie = movieResult
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(
            LayoutInflater.from(parent.context), parent
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
                oldItem.title == newItem.title
        }
    }

}