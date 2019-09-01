package me.mrizkip.moviecatalogue.movie


import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie

class MovieFragment : Fragment() {
    private lateinit var movieTitles: Array<String>
    private lateinit var movieDescriptions: Array<String>
    private lateinit var movieReleaseDates: Array<String>
    private lateinit var movieRatings: Array<String>
    private lateinit var moviePosters: TypedArray
    private lateinit var movieRuntimes: Array<String>
    private lateinit var movieGenres: Array<String>
    private var movieList: ArrayList<Movie> = arrayListOf()
    private lateinit var adapter: MovieAdapter

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.movie_recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        adapter = MovieAdapter(context, movieList) {
            val intent = Intent(context, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        view.movie_recyclerView.adapter = adapter

        getMovieData()
//        prepare()
//        addItem()
    }

    private fun getMovieData() {
        movieList.clear()
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        viewModel.getStatus().observe(this, Observer { status ->
            // set loading
        })

        viewModel.getMovieData().observe(this, Observer { movies ->
            movies?.let {
                movieList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun prepare() {
//        movieTitles = resources.getStringArray(R.array.movie_titles)
//        movieDescriptions = resources.getStringArray(R.array.movie_descriptions)
//        movieReleaseDates = resources.getStringArray(R.array.movie_release_dates)
//        movieRatings = resources.getStringArray(R.array.movie_rating)
//        moviePosters = resources.obtainTypedArray(R.array.movie_posters)
//        movieRuntimes = resources.getStringArray(R.array.movie_runtime)
//        movieGenres = resources.getStringArray(R.array.movie_genre)
    }

    private fun addItem() {
//        movieList.clear()
//
//        for (i in movieTitles.indices) {
//            val title = movieTitles[i]
//            val desc = movieDescriptions[i]
//            val releaseDate = movieReleaseDates[i]
//            val rating = movieRatings[i]
//            val poster = moviePosters.getResourceId(i, -1)
//            val runtime = movieRuntimes[i]
//            val genre = movieGenres[i]
//            val movie = Movie(title, desc, releaseDate, rating, poster, runtime, genre)
//            movieList.add(movie)
//        }
//
//        adapter.notifyDataSetChanged()
    }
}
