package me.mrizkip.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.model.Movies
import me.mrizkip.moviecatalogue.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieData = MutableLiveData<List<Movie>>()
    private var status = MutableLiveData<Boolean>()
    private val movieService by lazy {
        ApiRepository.getMovieService()
    }

    init {
        fetchMovie()
    }

    private fun fetchMovie() {
        movieService.discoverMovies(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    status.value = false
                    movieData.value = null
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful) {
                        status.value = true
                        movieData.value = response.body()?.movies
                    } else {
                        status.value = false
                        movieData.value = null
                    }
                }
            })
    }

    fun getStatus(): LiveData<Boolean> {
        return status
    }

    fun getMovieData(): LiveData<List<Movie>> {
        return movieData
    }

}
