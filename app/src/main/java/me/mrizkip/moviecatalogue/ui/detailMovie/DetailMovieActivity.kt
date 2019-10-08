package me.mrizkip.moviecatalogue.ui.detailMovie

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
import kotlinx.android.synthetic.main.activity_detail_movie.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.FavoriteMovie
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.ui.common.universalViewModelFactory
import me.mrizkip.moviecatalogue.util.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.sql.SQLClientInfoException

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }

    private lateinit var viewModel: DetailMovieViewModel
    private var mMovie: Movie? = null
    private var mMenu: Menu? = null
    private var favorited: Boolean = false
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(detailMovie_toolbar)
        supportActionBar?.apply {
            title = null
            setDisplayHomeAsUpEnabled(true)
        }

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

        viewModel = ViewModelProviders.of(
            this,
            universalViewModelFactory {
                DetailMovieViewModel(
                    movieId.toString()
                )
            })
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


        viewModel.getMovieData().observe(this, Observer { movie ->
            movie?.let {
                getFavorite()
                setFavoriteMenu()
                mMovie = it
                detailMovie_tvTitle.text = it.title
                detailMovie_tvDescription.text = it.overview
                detailMovie_tvReleaseDate.text = it.releaseDate
                detailMovie_tvUserRating.text = it.voteAverage.toString()
                detailMovie_tvGenre.text = it.genres?.get(0)?.name
                detailMovie_tvRuntime.text = it.runtime.toString()
                val moviePosterUrl = resources.getString(R.string.poster_url_huge)
                Picasso.get().load("$moviePosterUrl${it.posterPath}").resize(100, 140)
                    .centerCrop()
                    .into(detailMovie_imvPoster)
                detailMovie_imvCover.post {
                    val width = detailMovie_imvCover.width
                    val height = detailMovie_imvCover.height
                    Picasso.get().load("$moviePosterUrl${it.posterPath}").resize(width, height)
                        .centerCrop()
                        .into(detailMovie_imvCover)
                }
                val userRating = it.voteAverage!!.toFloat()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        mMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuDetail_addToFavorite -> {
                mMovie?.let {
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
                    FavoriteMovie.TABLE_FAVORITE_MOVIE,
                    FavoriteMovie.MOVIE_ID to mMovie?.id,
                    FavoriteMovie.TITLE to mMovie?.title,
                    FavoriteMovie.OVERVIEW to mMovie?.overview,
                    FavoriteMovie.BACKDROP_PATH to mMovie?.backdropPath,
                    FavoriteMovie.GENRE to mMovie?.genres?.get(0)?.name,
                    FavoriteMovie.POSTER_PATH to mMovie?.posterPath,
                    FavoriteMovie.RELEASE_DATE to mMovie?.releaseDate,
                    FavoriteMovie.RUNTIME to mMovie?.runtime,
                    FavoriteMovie.VOTE_AVERAGE to mMovie?.voteAverage
                )
            }
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, getString(R.string.detail_added_to_favorite), Snackbar.LENGTH_SHORT).show()
        } catch (err: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, err.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteMovie.TABLE_FAVORITE_MOVIE, "(MOVIE_ID = {id})",
                    "id" to movieId
                )
            }
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, getString(R.string.detail_removed_from_favorite), Snackbar.LENGTH_SHORT).show()
        } catch (err: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, err.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun setFavoriteMenu() {
        if (favorited)
            mMenu?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            mMenu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun getFavorite() {
        try {
            database.use {
                val result = select(FavoriteMovie.TABLE_FAVORITE_MOVIE)
                    .whereArgs(
                        "(MOVIE_ID = {id})",
                        "id" to movieId
                    )
                val favorite = result.parseList(classParser<FavoriteMovie>())
                if (favorite.isNotEmpty()) favorited = true
            }
        } catch (e: SQLClientInfoException) {
            val content: View = findViewById(android.R.id.content)
            Snackbar.make(content, e.localizedMessage as CharSequence, Snackbar.LENGTH_SHORT).show()
        }
    }
}
