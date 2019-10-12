package me.mrizkip.moviecatalogue.api

import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/discover/movie?")
    fun discoverMovies(@Query("api_key") api_key: String): Call<Movies>

    @GET("/3/movie/{id}?")
    fun getDetailMovie(@Path("id") id: String, @Query("api_key") api_key: String): Call<Movie>

    @GET("/3/search/movie?")
    fun searchMovie(@Query("api_key") api_key: String, @Query("query") movieName: String): Call<Movies>

    @GET("/3/discover/movie?")
    fun todayRelease(@Query("api_key") api_key: String,
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String
    ): Call<Movies>
}