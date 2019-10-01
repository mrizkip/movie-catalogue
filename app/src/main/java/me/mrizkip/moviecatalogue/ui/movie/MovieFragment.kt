package me.mrizkip.moviecatalogue.ui.movie


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie
import me.mrizkip.moviecatalogue.ui.detailMovie.DetailMovieActivity

class MovieFragment : Fragment() {
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

        viewModel = ViewModelProviders.of(activity!!).get(MovieViewModel::class.java)

        adapter = MovieAdapter(context, movieList) {
            val intent = Intent(context, DetailMovieActivity::class.java)
                .putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, it.id)
            startActivity(intent)
        }

        view.movie_recyclerView.adapter = adapter

        getMovieData()
    }

    private fun getMovieData() {
        movieList.clear()

        view?.movie_progressBar?.visibility = View.VISIBLE
        viewModel.getStatus().observe(this, Observer { status ->
            if (status) {
                view?.movie_error?.visibility = View.GONE
                view?.movie_recyclerView?.visibility = View.VISIBLE
            } else {
                view?.movie_error?.visibility = View.VISIBLE
                view?.movie_recyclerView?.visibility = View.GONE
            }
            view?.movie_progressBar?.visibility = View.GONE
        })

        viewModel.getMovieData().observe(this, Observer { movies ->
            movies?.let {
                movieList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
