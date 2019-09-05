package me.mrizkip.moviecatalogue.ui.detailTvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTvShowViewModel(tvShowId: String) : ViewModel() {
    private var tvShowData = MutableLiveData<TvShow>()
    private var status = MutableLiveData<Boolean>()
    private val tvShowService by lazy {
        ApiRepository.getTvShowService()
    }

    init {
        fetchDetailTvShow(tvShowId)
    }

    private fun fetchDetailTvShow(tvShowId: String) {
        tvShowService.getDetailTvShow(tvShowId, BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<TvShow> {
                override fun onFailure(call: Call<TvShow>, t: Throwable) {
                    status.value = false
                    tvShowData.value = null
                }

                override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                    if (response.isSuccessful) {
                        status.value = true
                        tvShowData.value = response.body()
                    }
                }
            })
    }

    fun getStatus(): LiveData<Boolean> {
        return status
    }

    fun getTvShowData(): LiveData<TvShow> {
        return tvShowData
    }
}