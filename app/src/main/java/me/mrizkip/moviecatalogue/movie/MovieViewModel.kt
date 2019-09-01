package me.mrizkip.moviecatalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieData = MutableLiveData<List<Movie>>()
    private var status = MutableLiveData<Boolean>()

    init {
        fetchMovie()
    }

    private fun fetchMovie() {
        ApiRepository().getMovieService().getNowPlayingMovies(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<List<Movie>> {
                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    status.value = false
                    movieData.value = null
                }

                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    if (response.isSuccessful) {
                        status.value = true
                        movieData.value = response.body()
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
