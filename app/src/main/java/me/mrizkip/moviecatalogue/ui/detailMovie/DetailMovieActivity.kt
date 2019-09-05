package me.mrizkip.moviecatalogue.ui.detailMovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.ui.universalViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(detailMovie_toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }

        val movieId: Int = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

        viewModel = ViewModelProviders.of(
            this,
            universalViewModelFactory { DetailMovieViewModel(movieId.toString()) })
            .get(DetailMovieViewModel::class.java)

        fetchDetailMovie()

    }

    private fun fetchDetailMovie() {
        detailMovie_progressBar?.visibility = View.VISIBLE
        viewModel.getStatus().observe(this, Observer { status ->
            if (status) {
                detailMovie_error?.visibility = View.GONE
            } else {
                detailMovie_error?.visibility = View.VISIBLE
            }
            detailMovie_progressBar?.visibility = View.GONE
        })

        viewModel.getDetailMovieData().observe(this, Observer { movie ->
            movie?.let {
                detailMovie_tvTitle.text = movie.title
                detailMovie_tvDescription.text = movie.overview
                detailMovie_tvReleaseDate.text = movie.releaseDate
                detailMovie_tvUserRating.text = movie.voteAverage.toString()
                detailMovie_tvGenre.text = movie.genres?.get(0)?.name
                detailMovie_tvRuntime.text = movie.runtime.toString()
                val moviePosterUrl = "https://image.tmdb.org/t/p/w500"
                Picasso.get().load("$moviePosterUrl${movie.posterPath}").resize(100, 140)
                    .centerCrop()
                    .into(detailMovie_imvPoster)
                detailMovie_imvCover.post {
                    val width = detailMovie_imvCover.width
                    val height = detailMovie_imvCover.height
                    Picasso.get().load("$moviePosterUrl${movie.posterPath}").resize(width, height)
                        .centerCrop()
                        .into(detailMovie_imvCover)
                }
                val userRating = movie.voteAverage!!.toFloat()
                when {
                    userRating >= 7 -> detailMovie_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_good_detail)
                    userRating >= 4 && userRating < 7 -> detailMovie_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_medium_detail)
                    userRating < 4 -> detailMovie_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_bad_detail)
                }
            }
            detailMovie_progressBar?.visibility = View.GONE
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
