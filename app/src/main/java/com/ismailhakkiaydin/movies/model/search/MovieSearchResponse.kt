package com.ismailhakkiaydin.movies.model.search

import com.google.gson.annotations.SerializedName
import com.ismailhakkiaydin.movies.model.videos.MovieVideoResult

data class MovieSearchResponse(
    @SerializedName("results")
    var results: List<MovieSearchResult>
)