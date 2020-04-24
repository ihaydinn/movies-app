package com.ismailhakkiaydin.movies.ui.detail.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseFragment
import com.ismailhakkiaydin.movies.databinding.FragmentMovieDetailBinding
import com.ismailhakkiaydin.movies.model.detail.MovieDetailResponse
import com.ismailhakkiaydin.movies.model.movie.MovieResult
import com.ismailhakkiaydin.movies.util.Constant
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_top_rated.*


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_movie_detail
    override fun getViewModel(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var movie = arguments?.getParcelable<MovieResult>("movie_details")
        dataBinding.detail = movie
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var movieDetailResponse = arguments?.getParcelable<MovieResult>("movie_details")

        viewModel.getMovieDetails(movieDetailResponse?.movieId)
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                dataBinding.content = it
            }
        })

        viewModel.getMovieTrailers(movieDetailResponse?.movieId)
        viewModel.movieTrailers.observe(viewLifecycleOwner, Observer{
            it?.let {
                recyclerviewTrailer.adapter = TrailerAdapter(it){
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(Constant.YOUTUBE_WATCH_URL + it.key)
                    startActivity(intent)
                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    progressBarTrailer.visibility = View.VISIBLE
                    recyclerviewTrailer.visibility = View.GONE
                    progressBarDetail.visibility = View.VISIBLE
                    lytDetail.visibility = View.GONE
                } else {
                    progressBarTrailer.visibility = View.GONE
                    recyclerviewTrailer.visibility = View.VISIBLE
                    progressBarDetail.visibility = View.GONE
                    lytDetail.visibility = View.VISIBLE
                }
            }
        })

    }

}
