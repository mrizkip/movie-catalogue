package me.mrizkip.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }

    private lateinit var imvPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvVote: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imvPoster = findViewById(R.id.detail_imvPoster)
        tvTitle = findViewById(R.id.detail_tvTitle)
        tvDescription = findViewById(R.id.detail_tvDescription)
        tvReleaseDate = findViewById(R.id.detail_tvReleaseDate)
        tvVote = findViewById(R.id.detail_tvVote)

        val movie: Movie = intent.getParcelableExtra(EXTRA_MOVIE)

        tvTitle.text = movie.title
        tvDescription.text = movie.description
        tvReleaseDate.text = movie.releaseDate
        tvVote.text = movie.voteAverage
        Picasso.get().load(movie.poster).resize(100, 140).centerCrop().into(imvPoster)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
