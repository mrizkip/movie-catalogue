package me.mrizkip.moviecatalogue.movie


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*

import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.model.MoviesData

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private var movieList: ArrayList<Movie> = arrayListOf()
    private lateinit var adapter: MovieAdapter

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

        movieList.addAll(MoviesData.listData)
        adapter = MovieAdapter(context, movieList) {
            val intent = Intent(context, DetailActivity::class.java)
                .putExtra(DetailActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }

        view.movie_recyclerView.adapter = adapter
    }
}
