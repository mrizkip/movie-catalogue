package me.mrizkip.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String = "",
    var description: String = "",
    var releaseDate: String = "",
    var userRating: String = "",
    var poster: Int = 0,
    var runtime: String = "",
    var genre: String = ""
) : Parcelable