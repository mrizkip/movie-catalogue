package me.mrizkip.moviecatalogue.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import me.mrizkip.moviecatalogue.model.FavoriteMovie
import me.mrizkip.moviecatalogue.model.FavoriteTvShow
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "MovieCatalogue.db", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteMovie.TABLE_FAVORITE_MOVIE, true,
            FavoriteMovie.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMovie.MOVIE_ID to TEXT + UNIQUE,
            FavoriteMovie.TITLE to TEXT,
            FavoriteMovie.OVERVIEW to TEXT,
            FavoriteMovie.BACKDROP_PATH to TEXT,
            FavoriteMovie.GENRE to TEXT,
            FavoriteMovie.POSTER_PATH to TEXT,
            FavoriteMovie.RELEASE_DATE to TEXT,
            FavoriteMovie.RUNTIME to TEXT,
            FavoriteMovie.VOTE_AVERAGE to TEXT
        )

        db?.createTable(
            FavoriteTvShow.TABLE_FAVORITE_TV_SHOW, true,
            FavoriteTvShow.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTvShow.TV_SHOW_ID to TEXT + UNIQUE,
            FavoriteTvShow.NAME to TEXT,
            FavoriteTvShow.OVERVIEW to TEXT,
            FavoriteTvShow.FIRST_AIR_DATE to TEXT,
            FavoriteTvShow.GENRE to TEXT,
            FavoriteTvShow.NUMBER_OF_SEASONS to TEXT,
            FavoriteTvShow.POSTER_PATH to TEXT,
            FavoriteTvShow.VOTE_AVERAGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMovie.TABLE_FAVORITE_MOVIE, true)
        db?.dropTable(FavoriteTvShow.TABLE_FAVORITE_TV_SHOW, true)
    }

}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)