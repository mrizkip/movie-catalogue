package me.mrizkip.moviecatalogue.model

import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("genres")
    var genres: List<Genre?>? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("number_of_seasons")
    var numberOfSeasons: Int? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
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