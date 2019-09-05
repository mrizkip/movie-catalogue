package me.mrizkip.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class TvShows(
    @SerializedName("results")
    val tvShows: List<TvShow>
)