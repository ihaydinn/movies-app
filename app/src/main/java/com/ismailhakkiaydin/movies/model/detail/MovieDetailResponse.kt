package com.ismailhakkiaydin.movies.model.detail

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("backdrop_path")
    var backdrop_path: String?=null,

    @SerializedName("overview")
    var overview: String?=null,

    @SerializedName("poster_path")
    var poster_path: String?=null,

    @SerializedName("release_date")
    var release_date: String?=null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("genres")
    var genres: List<MovieGenre>?=null,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var vote_average: Double,

    @SerializedName("vote_count")
    var vote_count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(MovieGenre),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(backdrop_path)
        parcel.writeString(overview)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
        parcel.writeTypedList(genres)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeInt(vote_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailResponse> {
        override fun createFromParcel(parcel: Parcel): MovieDetailResponse {
            return MovieDetailResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailResponse?> {
            return arrayOfNulls(size)
        }
    }
}