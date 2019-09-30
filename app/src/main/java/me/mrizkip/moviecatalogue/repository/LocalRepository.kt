package me.mrizkip.moviecatalogue.repository

import android.content.Context
import me.mrizkip.moviecatalogue.model.FavoriteMovie
import me.mrizkip.moviecatalogue.model.FavoriteTvShow
import me.mrizkip.moviecatalogue.util.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class LocalRepository(private val context: Context) {

    fun getFavoriteMovies(): List<FavoriteMovie> {
        var movieList: List<FavoriteMovie> = arrayListOf()
        context.database.use {
            val result = select(FavoriteMovie.TABLE_FAVORITE_MOVIE)
            movieList = result.parseList(classParser())
        }
        return movieList
    }

    fun getFavoriteTvShows(): List<FavoriteTvShow> {
        var tvShowList: List<FavoriteTvShow> = arrayListOf()
        context.database.use {
            val result = select(FavoriteTvShow.TABLE_FAVORITE_TV_SHOW)
            tvShowList = result.parseList(classParser())
        }
        return tvShowList
    }
}