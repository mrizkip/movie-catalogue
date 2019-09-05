package me.mrizkip.moviecatalogue.ui.detailMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel(movieId: String) : ViewModel() {
    private var movieData = MutableLiveData<Movie>()
    private var status = MutableLiveData<Boolean>()

    init {
        fetchDetailMovie(movieId)
    }

    private fun fetchDetailMovie(movieId: String) {
        ApiRepository().getMovieService().getDetailMovie(movieId, BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    status.value = false
                    movieData.value = null
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
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

    fun getDetailMovieData(): LiveData<Movie> {
        return movieData
    }
}