package com.ismailhakkiaydin.movies.model.search

import com.google.gson.annotations.SerializedName

data class MovieSearchResult(
    @SerializedName("vote_count")
    var vote_count : Int,
    @SerializedName("poster_path")
    var poster_path : String?=null,
    @SerializedName("backdrop_path")
    var backdrop_path : String?=null,
    @SerializedName("title")
    var title : String?=null,
    @SerializedName("overview")
    var overview : String?=null,
    @SerializedName("vote_average")
    var vote_average : String?=null,
    @SerializedName("release_date")
    var release_date : String?=null

)