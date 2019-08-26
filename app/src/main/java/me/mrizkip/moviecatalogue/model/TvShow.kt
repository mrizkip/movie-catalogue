package me.mrizkip.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow (
    var title: String = "",
    var description: String = "",
    var releaseDate: String = "",
    var userRating: String = "",
    var poster: Int = 0,
    var seasons: String = "",
    var genre: String = ""
) : Parcelable