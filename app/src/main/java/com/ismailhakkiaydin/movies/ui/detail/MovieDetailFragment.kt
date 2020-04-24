package com.ismailhakkiaydin.movies.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseFragment
import com.ismailhakkiaydin.movies.databinding.FragmentMovieDetailBinding
import com.ismailhakkiaydin.movies.model.movie.MovieResult
import com.ismailhakkiaydin.movies.util.Constant
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    private lateinit var genreAdapter: GenreAdapter

    private var movie: MovieResult? = null
    private var isFav: Boolean? = null

    override fun getLayoutRes(): Int = R.layout.fragment_movie_detail
    override fun getViewModel(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        arguments?.let {
            movie = it?.getParcelable("movie_details")
            dataBinding.detail = movie
            checkFav()
            dataBinding.imgFavorite.setOnClickListener {
                favorite()
            }
        }

        /*
        var movie = arguments?.getParcelable<MovieResult>("movie_details")
        dataBinding.detail = movie*/
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var movieDetailResponse = arguments?.getParcelable<MovieResult>("movie_details")

        viewModel.getMovieDetails(movieDetailResponse?.movieId)
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                dataBinding.content = it

                genreAdapter =
                    GenreAdapter(it.genres!!)
                recyclerviewGenres.adapter = genreAdapter

            }
        })

        viewModel.getMovieTrailers(movieDetailResponse?.movieId)
        viewModel.movieTrailers.observe(viewLifecycleOwner, Observer{
            it?.let {
                recyclerviewTrailer.adapter =
                    TrailerAdapter(
                        it
                    ) {
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

    private fun favorite(){
        if (isFav!!){
            viewModel.deleteMovie(movie)
            Toast.makeText(context!!, "Removed", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.insertMovie(movie)
            Toast.makeText(context!!, "Added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFav(){
        viewModel.getSingleMovie(movie?.movieId).observe(viewLifecycleOwner, Observer {
            if (it != null){
                dataBinding.imgFavorite.setImageResource(R.drawable.ic_favorite)
                isFav = true
            }else{
                dataBinding.imgFavorite.setImageResource(R.drawable.ic_favorite_border)
                isFav = false
            }
        })
    }

}
