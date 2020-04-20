package com.ismailhakkiaydin.movies.model.reviews

import com.google.gson.annotations.SerializedName

data class MovieReviewResult(
    @SerializedName("id")
    var id: String,

    @SerializedName("author")
    var author: String,

    @SerializedName("content")
    var content: String
)
