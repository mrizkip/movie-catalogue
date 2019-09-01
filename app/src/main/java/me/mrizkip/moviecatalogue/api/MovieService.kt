package me.mrizkip.moviecatalogue.api

import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/now_playing?")
    fun getNowPlayingMovies(@Query("api_key") api_key: String): Call<List<Movie>>

    @GET("/3/movie/{id}?")
    fun getDetailMovie(@Path("id") id: String, @Query("api_key") api_key: String): Call<Movie>
}