package me.mrizkip.moviecatalogue.ui.detailTvShow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.ui.universalViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "EXTRA_TV_SHOW_ID"
    }

    private lateinit var viewModel: DetailTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        setSupportActionBar(detailTvShow_toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }

        val tvShowId: Int = intent.getIntExtra(EXTRA_TV_SHOW_ID, -1)

        viewModel = ViewModelProviders.of(
            this,
            universalViewModelFactory { DetailTvShowViewModel(tvShowId.toString()) })
            .get(DetailTvShowViewModel::class.java)

        fetchDetailTvShow()
    }

    private fun fetchDetailTvShow() {
        detailTvShow_progressBar?.visibility = View.VISIBLE
        viewModel.getStatus().observe(this, Observer { status ->
            if (status) {
                detailTvShow_error?.visibility = View.GONE
            } else {
                detailTvShow_error?.visibility = View.VISIBLE
            }
            detailTvShow_progressBar?.visibility = View.GONE
        })

        viewModel.getTvShowData().observe(this, Observer { tvShow ->
            tvShow?.let {
                detailTvShow_tvTitle.text = it.name
                detailTvShow_tvDescription.text = it.overview
                detailTvShow_tvReleaseDate.text = it.firstAirDate
                detailTvShow_tvUserRating.text = it.voteAverage.toString()
                detailTvShow_tvGenre.text = it.genres?.get(0)?.name
                val season = getString(R.string.tv_show_adapter_seasons, it.numberOfSeasons.toString())
                detailTvShow_tvSeasons.text = season
                val tvShowPosterUrl = resources.getString(R.string.poster_url_small)
                Picasso.get().load("$tvShowPosterUrl${it.posterPath}").resize(100, 140).centerCrop()
                    .into(detailTvShow_imvPoster)
                detailTvShow_imvCover.post {
                    val width = detailTvShow_imvCover.width
                    val height = detailTvShow_imvCover.height
                    Picasso.get().load("$tvShowPosterUrl${it.posterPath}").resize(width, height).centerCrop()
                        .into(detailTvShow_imvCover)
                }
                val userRating = tvShow.voteAverage!!.toFloat()
                when {
                    userRating >= 7 -> detailTvShow_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_good_detail)
                    userRating >= 4 && userRating < 7 -> detailTvShow_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_medium_detail)
                    userRating < 4 -> detailTvShow_tvUserRating.background =
                        ContextCompat.getDrawable(this, R.drawable.background_rating_bad_detail)
                }
            }
            detailTvShow_progressBar?.visibility = View.GONE
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
