package com.ismailhakkiaydin.movies.model.detail

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieGenre (
    @SerializedName("id")
    var genresId: Int,

    @SerializedName("name")
    var name: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(genresId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieGenre> {
        override fun createFromParcel(parcel: Parcel): MovieGenre {
            return MovieGenre(parcel)
        }

        override fun newArray(size: Int): Array<MovieGenre?> {
            return arrayOfNulls(size)
        }
    }
}