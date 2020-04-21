package com.ismailhakkiaydin.movies.ui.main.toprated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseVMFragment
import com.ismailhakkiaydin.movies.ui.main.MovieAdapter

import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : BaseVMFragment<TopRatedViewModel>() {

    private val adapter = MovieAdapter()

    override fun getViewModel(): Class<TopRatedViewModel> = TopRatedViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerViewTopRated.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        recyclerViewTopRated.adapter = adapter
        viewModel.getTopRatedMovies()

        viewModel.topRatedMovie.observe(viewLifecycleOwner, Observer { topRatedMovie ->
            topRatedMovie?.let {
                recyclerViewTopRated.visibility = View.VISIBLE
                adapter.submitList(topRatedMovie)
            }

        })

        viewModel.loadingTopRatedMovie.observe(viewLifecycleOwner, Observer { loadingTopRated ->
            loadingTopRated?.let {
                if (it){
                    progressBar.visibility = View.VISIBLE
                    recyclerViewTopRated.visibility = View.GONE
                }else{
                    progressBar.visibility = View.GONE
                }
            }
        })

    }
}
