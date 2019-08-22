package me.mrizkip.moviecatalogue

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var movieTitles: Array<String>
    private lateinit var movieDescriptions: Array<String>
    private var movieReleaseDates = emptyArray<String>()
    private var movieVotes = emptyArray<String>()
    private lateinit var moviePosters: TypedArray
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(this)

        val listView: ListView = findViewById(R.id.main_list_view)
        listView.adapter = movieAdapter

        prepare()
        addItem()
    }

    private fun prepare() {
        movieTitles = resources.getStringArray(R.array.movie_titles)
        movieDescriptions = resources.getStringArray(R.array.movie_descriptions)
        movieReleaseDates = resources.getStringArray(R.array.movie_release_dates)
        movieVotes = resources.getStringArray(R.array.movie_votes)
        moviePosters = resources.obtainTypedArray(R.array.movie_posters)
    }

    private fun addItem() {
        movieList = arrayListOf()

        for (i in movieTitles.indices ) {
            val title = movieTitles[i]
            val desc = movieDescriptions[i]
            val releaseDate = movieReleaseDates[i]
            val vote = movieVotes[i]
            val poster = moviePosters.getResourceId(i, -1)
            val movie = Movie(title, releaseDate, desc, poster, vote)
            movieList.add(movie)
        }

        movieAdapter.movies = movieList
    }
}
