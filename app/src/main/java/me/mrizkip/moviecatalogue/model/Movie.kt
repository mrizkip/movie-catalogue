package me.mrizkip.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Movie(
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.toDouble(),
    @SerializedName("runtime")
    var runtime: Int? = 0
) : Parcelable