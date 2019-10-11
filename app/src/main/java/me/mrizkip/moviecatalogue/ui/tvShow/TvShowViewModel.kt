package me.mrizkip.moviecatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.model.TvShows
import me.mrizkip.moviecatalogue.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel : ViewModel() {
    private var tvShowData = MutableLiveData<List<TvShow>>()
    private var status = MutableLiveData<Boolean>()
    private val tvShowService by lazy {
        ApiRepository.getTvShowService()
    }

    init {
        fetchTvShows()
    }

    fun fetchTvShows() {
        tvShowService.discoverTvShows(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<TvShows> {
                override fun onFailure(call: Call<TvShows>, t: Throwable) {
                    status.value = false
                    tvShowData.value = null
                }

                override fun onResponse(call: Call<TvShows>, response: Response<TvShows>) {
                    if (response.isSuccessful) {
                        status.value = true
                        tvShowData.value = response.body()?.tvShows
                    } else {
                        status.value = false
                        tvShowData.value = null
                    }
                }
            })
    }

    fun getStatus(): LiveData<Boolean> {
        return status
    }

    fun getTvShowsData(): LiveData<List<TvShow>> {
        return tvShowData
    }
}