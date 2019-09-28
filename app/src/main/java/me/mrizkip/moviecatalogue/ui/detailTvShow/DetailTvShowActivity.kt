package me.mrizkip.moviecatalogue.ui.detailTvShow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.FavoriteTvShow
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.ui.common.universalViewModelFactory
import me.mrizkip.moviecatalogue.util.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.sql.SQLClientInfoException

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW_ID = "EXTRA_TV_SHOW_ID"
    }

    private lateinit var viewModel: DetailTvShowViewModel
    private var mTvShow: TvShow? = null
    private var mMenu: Menu? = null
    private var favorited: Boolean = false

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
            universalViewModelFactory {
                DetailTvShowViewModel(
                    tvShowId.toString()
                )
            })
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
        getFavorite()
        setFavoriteMenu()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        mMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuDetail_addToFavorite -> {
                mTvShow?.let {
                    if (!favorited) addFavorite() else removeFromFavorite()
                    favorited = !favorited
                    setFavoriteMenu()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTvShow.TABLE_FAVORITE_TV_SHOW,
                    FavoriteTvShow.TV_SHOW_ID to mTvShow?.id,
                    FavoriteTvShow.NAME to mTvShow?.name,
                    FavoriteTvShow.OVERVIEW to mTvShow?.overview,
                    FavoriteTvShow.FIRST_AIR_DATE to mTvShow?.firstAirDate,
                    FavoriteTvShow.GENRE to mTvShow?.genres?.get(0),
                    FavoriteTvShow.NUMBER_OF_SEASONS to mTvShow?.numberOfSeasons,
                    FavoriteTvShow.POSTER_PATH to mTvShow?.posterPath,
                    FavoriteTvShow.VOTE_AVERAGE to mTvShow?.voteAverage
                )
            }
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (err: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, err.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTvShow.TABLE_FAVORITE_TV_SHOW, "(TV_SHOW_ID = {id})",
                    "id" to mTvShow?.id.toString()
                )
            }
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, "Removed to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (err: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, err.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavoriteMenu() {
        if (favorited)
            mMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            mMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun getFavorite() {
        try {
            database.use {
                val result = select(FavoriteTvShow.TABLE_FAVORITE_TV_SHOW)
                    .whereArgs(
                        "(TV_SHOW_ID = {id})",
                        "id" to mTvShow?.id.toString()
                    )
                val favorite = result.parseList(classParser<FavoriteTvShow>())
                if (favorite.isNotEmpty()) favorited = true
            }
        } catch (e: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, e.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT).show()
        }
    }
}
