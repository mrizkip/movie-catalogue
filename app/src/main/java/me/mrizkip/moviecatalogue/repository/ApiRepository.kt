package me.mrizkip.moviecatalogue.repository

import com.google.gson.Gson
import me.mrizkip.moviecatalogue.BuildConfig
import me.mrizkip.moviecatalogue.api.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRepository {
    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttp = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            writeTimeout(20, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
        }

        return okHttp.build()
    }

    private fun getGson(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    private fun getRetrofit(): Retrofit {
        val client = getOkHttpClient()
        val gson = getGson()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(gson)
            .build()
    }

    fun getMovieService(): MovieService {
        val retrofit = getRetrofit()
        return retrofit.create(MovieService::class.java)
    }
}