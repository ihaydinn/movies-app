package com.ismailhakkiaydin.movies.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ismailhakkiaydin.movies.R
import com.ismailhakkiaydin.movies.databinding.ItemSearchBinding
import com.ismailhakkiaydin.movies.model.search.MovieSearchResult

class SearchAdapter(
    val searchList: List<MovieSearchResult>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchBinding>(
            inflater,
            R.layout.item_search,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.search = searchList[position]
    }
}