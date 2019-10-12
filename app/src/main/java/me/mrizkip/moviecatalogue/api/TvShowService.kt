package me.mrizkip.moviecatalogue.api

import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.model.TvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET("/3/discover/tv?")
    fun discoverTvShows(@Query("api_key") api_key: String): Call<TvShows>

    @GET("/3/tv/{id}?")
    fun getDetailTvShow(@Path("id") id: String, @Query("api_key") api_key: String): Call<TvShow>

    @GET("/3/search/tv?")
    fun searchTvShow(@Query("api_key") api_key: String, @Query("query") tvShowName: String) : Call<TvShows>
}