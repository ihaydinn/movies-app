package com.ismailhakkiaydin.movies.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseVMFragment
import com.ismailhakkiaydin.movies.ui.detail.MovieDetailViewModel
import com.ismailhakkiaydin.movies.ui.main.MovieAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : BaseVMFragment<MovieDetailViewModel>() {

    override fun getViewModel(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerviewFavorites.layoutManager = GridLayoutManager(context!!, 2)
                recyclerviewFavorites.adapter = MovieAdapter(it) {
                    val bundle = bundleOf("movie_details" to it)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_favoriteFragment_to_movieDetailFragment, bundle)
                }
            }

        })
    }
}
