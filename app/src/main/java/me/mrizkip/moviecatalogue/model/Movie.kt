package me.mrizkip.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("genres")
    var genres: List<Genre?>? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("runtime")
    var runtime: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null
) {
    data class Genre(
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("name")
        var name: String? = null
    )
}