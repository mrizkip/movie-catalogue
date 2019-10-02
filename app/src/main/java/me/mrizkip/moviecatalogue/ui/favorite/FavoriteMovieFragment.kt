package me.mrizkip.moviecatalogue.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite_movie.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.repository.LocalRepository
import me.mrizkip.moviecatalogue.ui.common.universalViewModelFactory
import me.mrizkip.moviecatalogue.ui.detailMovie.DetailMovieActivity
import me.mrizkip.moviecatalogue.ui.movie.MovieAdapter

class FavoriteMovieFragment : Fragment() {
    private var movieList: ArrayList<Movie> = arrayListOf()
    private lateinit var adapter: MovieAdapter

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var localRepository: LocalRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.favoriteMovie_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        localRepository = LocalRepository(context)
        viewModel = ViewModelProviders.of(this,
            universalViewModelFactory {
                FavoriteViewModel(localRepository)
            }).get(FavoriteViewModel::class.java)

        adapter = MovieAdapter(context, movieList) {
            val intent = Intent(context, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, it.id)
            startActivity(intent)
        }

        view.favoriteMovie_recyclerView.adapter = adapter

        getFavoriteMovies()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        view?.favoriteMovie_progressBar?.visibility = View.VISIBLE

        viewModel.getFavoriteMovies().observe(this, Observer { movies ->
            movies?.let {
                movieList.clear()
                if (it.isNotEmpty()) {
                    view?.favoriteMovie_error?.visibility = View.GONE
                    view?.favoriteMovie_recyclerView?.visibility = View.VISIBLE
                    movieList.addAll(it)
                    adapter.notifyDataSetChanged()
                } else {
                    view?.favoriteMovie_error?.visibility = View.VISIBLE
                    view?.favoriteMovie_recyclerView?.visibility = View.GONE
                }
            }
            view?.favoriteMovie_progressBar?.visibility = View.GONE
        })
    }
}
