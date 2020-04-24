package com.ismailhakkiaydin.movies.model.movie

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("id")
    var movieId: Int,

    @SerializedName("poster_path")
    var poster_path: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    var release_date: String? = null,

    @SerializedName("original_title")
    var original_title: String? = null,

    @SerializedName("original_language")
    var original_language: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("vote_count")
    var vote_count: Int,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(movieId)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(release_date)
        parcel.writeString(original_title)
        parcel.writeString(original_language)
        parcel.writeString(title)
        parcel.writeString(backdrop_path)
        parcel.writeDouble(popularity)
        parcel.writeInt(vote_count)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieResult> {
        override fun createFromParcel(parcel: Parcel): MovieResult {
            return MovieResult(parcel)
        }

        override fun newArray(size: Int): Array<MovieResult?> {
            return arrayOfNulls(size)
        }
    }
}
