package com.ismailhakkiaydin.movies.ui.main.search

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.SearchView
import androidx.core.os.bundleOf

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.common.BaseFragment
import com.ismailhakkiaydin.movies.databinding.FragmentSearchBinding
import com.ismailhakkiaydin.movies.util.gone
import com.ismailhakkiaydin.movies.util.visible
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dataBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.length > 0){
                    viewModel.getSearchMovies(query!!)
                    dataBinding.imgSearch.gone()
                    dataBinding.tvSearch.gone()
                    dataBinding.recyclerviewSearch.visible()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        dataBinding.searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                dataBinding.recyclerviewSearch.gone()
                dataBinding.imgSearch.visible()
                dataBinding.tvSearch.visible()
                return false
            }
        })

        viewModel.searchMovies.observe(viewLifecycleOwner, Observer {searchMovie ->
            searchMovie?.let {
                recyclerviewSearch.layoutManager = LinearLayoutManager(context!!)
                recyclerviewSearch.adapter = SearchAdapter(searchMovie)
            }

        })
    }


}
