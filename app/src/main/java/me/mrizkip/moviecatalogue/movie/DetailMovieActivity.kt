package me.mrizkip.moviecatalogue.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(detailMovie_toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }

        val movie: Movie = intent.getParcelableExtra(EXTRA_MOVIE)

        detailMovie_tvTitle.text = movie.title
        detailMovie_tvDescription.text = movie.description
        detailMovie_tvReleaseDate.text = movie.releaseDate
        detailMovie_tvUserRating.text = movie.userRating
        detailMovie_tvGenre.text = movie.genre
        detailMovie_tvRuntime.text = movie.runtime
        Picasso.get().load(movie.poster).resize(100, 140).centerCrop().into(detailMovie_imvPoster)
        detailMovie_imvCover.post {
            val width = detailMovie_imvCover.width
            val height = detailMovie_imvCover.height
            Picasso.get().load(movie.poster).resize(width, height).centerCrop().into(detailMovie_imvCover)
        }
        val userRating = movie.userRating.toFloat()
        when {
            userRating >= 7 -> detailMovie_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_good_detail)
            userRating >= 4 && userRating < 7 -> detailMovie_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_medium_detail)
            userRating < 4 -> detailMovie_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_bad_detail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
