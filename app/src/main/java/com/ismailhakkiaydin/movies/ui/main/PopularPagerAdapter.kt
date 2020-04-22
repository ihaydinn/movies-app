package com.ismailhakkiaydin.movies.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.databinding.ItemPopularViewPagerBinding
import com.ismailhakkiaydin.movies.model.movie.MovieResult

class PopularPagerAdapter(var popularMovieList: List<MovieResult>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = DataBindingUtil.inflate<ItemPopularViewPagerBinding>(
            inflater,
            R.layout.item_popular_view_pager,
            container,
            false
        )
        binding.popular = popularMovieList[position]//binding.imageView.setImageResource(images[position])
        container.addView(binding.root)

        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = popularMovieList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}