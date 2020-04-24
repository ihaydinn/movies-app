package com.ismailhakkiaydin.movies.ui.main.toprated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseFragment
import com.ismailhakkiaydin.movies.common.BaseVMFragment
import com.ismailhakkiaydin.movies.databinding.FragmentTopRatedBinding
import com.ismailhakkiaydin.movies.ui.main.MovieAdapter

import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : BaseFragment<FragmentTopRatedBinding, TopRatedViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_top_rated
    override fun getViewModel(): Class<TopRatedViewModel> = TopRatedViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getTopRatedMovies()

        viewModel.topRatedMovie.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerViewTopRated.visibility = View.VISIBLE
                recyclerViewTopRated.layoutManager = LinearLayoutManager(context!!)
                recyclerViewTopRated.adapter = MovieAdapter(it) {
                    val bundle = bundleOf("movie_details" to it)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_topRatedFragment_to_movieDetailFragment, bundle)
                }
            }
        })

        viewModel.loadingTopRatedMovie.observe(viewLifecycleOwner, Observer { loadingTopRated ->
            loadingTopRated?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                    recyclerViewTopRated.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }
}
