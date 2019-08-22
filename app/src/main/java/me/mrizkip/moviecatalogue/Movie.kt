package me.mrizkip.moviecatalogue

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val releaseDate: String,
    val description: String,
    val poster: Int,
    val voteAverage: String
) : Parcelable