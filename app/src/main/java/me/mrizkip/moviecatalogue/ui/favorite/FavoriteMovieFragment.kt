package me.mrizkip.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.ui.movie.MovieAdapter

class FavoriteMovieFragment : Fragment() {
    private var movieList: ArrayList<Movie> = arrayListOf()
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
