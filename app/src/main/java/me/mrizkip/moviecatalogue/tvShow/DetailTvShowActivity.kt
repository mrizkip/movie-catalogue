package me.mrizkip.moviecatalogue.tvShow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        setSupportActionBar(detailTvShow_toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }

        val tvShow: TvShow = intent.getParcelableExtra(EXTRA_TV_SHOW)

        detailTvShow_tvTitle.text = tvShow.title
        detailTvShow_tvDescription.text = tvShow.description
        detailTvShow_tvReleaseDate.text = tvShow.releaseDate
        detailTvShow_tvUserRating.text = tvShow.userRating
        detailTvShow_tvGenre.text = tvShow.genre
        val season = getString(R.string.tv_show_adapter_seasons, tvShow.seasons)
        detailTvShow_tvSeasons.text = season
        Picasso.get().load(tvShow.poster).resize(100, 140).centerCrop().into(detailTvShow_imvPoster)
        detailTvShow_imvCover.post {
            val width = detailTvShow_imvCover.width
            val height = detailTvShow_imvCover.height
            Picasso.get().load(tvShow.poster).resize(width, height).centerCrop()
                .into(detailTvShow_imvCover)
        }
        val userRating = tvShow.userRating.toFloat()
        when {
            userRating >= 7 -> detailTvShow_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_good_detail)
            userRating >= 4 && userRating < 7 -> detailTvShow_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_medium_detail)
            userRating < 4 -> detailTvShow_tvUserRating.background =
                ContextCompat.getDrawable(this, R.drawable.background_rating_bad_detail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
