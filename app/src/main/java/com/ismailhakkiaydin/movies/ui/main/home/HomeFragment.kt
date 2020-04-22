package com.ismailhakkiaydin.movies.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseVMFragment
import com.ismailhakkiaydin.movies.ui.DepthPageTransformer
import com.ismailhakkiaydin.movies.ui.main.PopularPagerAdapter
import com.ismailhakkiaydin.movies.ui.main.UpcomingPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseVMFragment<HomeViewModel>() {

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPopularMovies()
        viewModel.getUpcomingMovies()

        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { popularMovie ->
            popularMovie?.let {
                lytHome.visibility = View.VISIBLE
                pagerPopularMovie.adapter = PopularPagerAdapter(popularMovie)
                pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())
            }
        })

        viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer { upcomingMovie ->
            upcomingMovie?.let {
                lytHome.visibility = View.VISIBLE
                pagerUpcomingMovie.adapter = UpcomingPagerAdapter(upcomingMovie)
                pagerUpcomingMovie.setPageTransformer(true, DepthPageTransformer())
            }
        })

        viewModel.loadingMovies.observe(viewLifecycleOwner, Observer { loadingMovies ->
            loadingMovies?.let {
                if (it){
                    homeProgressBar.visibility = View.VISIBLE
                    lytHome.visibility = View.GONE
                }else{
                    homeProgressBar.visibility = View.GONE
                }
            }
        })

    }
}
