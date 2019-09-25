package me.mrizkip.moviecatalogue.model

data class FavoriteTvShow (
    var id: Long?,
    var tvShowId: String?,
    var name: String?,
    var overview: String?,
    var firstAirDate: String?,
    var genre: String?,
    var numberOfSeasons: String?,
    var posterPath: String?,
    var voteAverage: String?
) {
    companion object {
        const val TABLE_FAVORITE_TV_SHOW: String = "TABLE_FAVORITE_TV_SHOW"
        const val ID: String = "ID"
        const val TV_SHOW_ID: String = "TV_SHOW_ID"
        const val NAME: String = "NAME"
        const val OVERVIEW: String = "OVERVIEW"
        const val FIRST_AIR_DATE: String = "FIRST_AIR_DATE"
        const val GENRE: String = "GENRE"
        const val NUMBER_OF_SEASONS: String = "NUMBER_OF_SEASONS"
        const val POSTER_PATH: String = "POSTER_PATH"
        const val VOTE_AVERAGE: String = "VOTE_AVERAGE"
    }
}