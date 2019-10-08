package me.mrizkip.moviecatalogue.model

data class FavoriteMovie (
    var id: Long?,
    var movieId: String?,
    var title: String?,
    var overview: String?,
    var backdropPath: String?,
    var genre: String?,
    var posterPath: String?,
    var releaseDate: String?,
    var runtime: String?,
    var voteAverage: String?
) {
    companion object {
        const val TABLE_FAVORITE_MOVIE: String = "TABLE_FAVORITE_MOVIE"
        const val ID: String = "ID"
        const val MOVIE_ID: String = "MOVIE_ID"
        const val TITLE: String = "TITLE"
        const val OVERVIEW: String = "OVERVIEW"
        const val BACKDROP_PATH: String = "BACKDROP_PATH"
        const val GENRE: String = "GENRE"
        const val POSTER_PATH: String = "POSTER_PATH"
        const val RELEASE_DATE: String = "RELEASE_DATE"
        const val RUNTIME: String = "RUNTIME"
        const val VOTE_AVERAGE: String = "VOTE_AVERAGE"
    }
}