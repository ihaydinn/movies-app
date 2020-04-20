package com.ismailhakkiaydin.movies.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("id")
    var movieId: Int,

    @SerializedName("poster_path")
    var poster_path: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var release_date: String,

    @SerializedName("original_title")
    var original_title: String,

    @SerializedName("original_language")
    var original_language: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var vote_count: Int,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double
)
