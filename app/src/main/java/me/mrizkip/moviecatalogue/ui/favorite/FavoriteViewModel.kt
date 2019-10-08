package me.mrizkip.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.repository.LocalRepository

class FavoriteViewModel(private val localRepository: LocalRepository) : ViewModel() {
    private var movieData = MutableLiveData<List<Movie>>()
    private var tvShowData = MutableLiveData<List<TvShow>>()

    init {
        fetchFavoriteMovies()
        fetchFavoriteTvShows()
    }

    fun fetchFavoriteMovies() {
        val movieList: ArrayList<Movie> = arrayListOf()
        val favoriteMovies = localRepository.getFavoriteMovies()
        for (movie in favoriteMovies) {
            val mMovie = Movie()
            mMovie.id = movie.movieId?.toInt()
            mMovie.title = movie.title
            mMovie.overview = movie.overview
            mMovie.posterPath = movie.posterPath
            mMovie.releaseDate = movie.releaseDate
            mMovie.voteAverage = movie.voteAverage?.toDouble()

            movieList.add(mMovie)
        }
        movieData.postValue(movieList)
    }

    fun fetchFavoriteTvShows() {
        val tvShowList: ArrayList<TvShow> = arrayListOf()
        val favoriteTvShows = localRepository.getFavoriteTvShows()
        for (tvShow in favoriteTvShows) {
            val mTvShow = TvShow()
            mTvShow.id = tvShow.tvShowId?.toInt()
            mTvShow.name = tvShow.name
            mTvShow.overview = tvShow.overview
            mTvShow.posterPath = tvShow.posterPath
            mTvShow.firstAirDate = tvShow.firstAirDate
            mTvShow.voteAverage = tvShow.voteAverage?.toDouble()

            tvShowList.add(mTvShow)
        }

        tvShowData.postValue(tvShowList)
    }

    fun getFavoriteMovies(): LiveData<List<Movie>> = movieData
    fun getFavoriteTvShows(): LiveData<List<TvShow>> = tvShowData
}