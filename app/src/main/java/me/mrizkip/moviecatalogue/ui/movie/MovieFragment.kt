package me.mrizkip.moviecatalogue.ui.movie


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.*
import android.widget.SearchView
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
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchMenu = menu.findItem(R.id.menuMain_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null

        searchMenu?.let { searchView = it.actionView as SearchView }

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.queryHint = "Search Movie"

        val handler = Handler()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.equals("")) {
                    movieList.clear()
                    adapter.notifyDataSetChanged()
                } else {
                    query?.let {
                        if (it.length > 2) {
                            handler.removeCallbacksAndMessages(null)
                            handler.postDelayed({
                                movieList.clear()
                                adapter.notifyDataSetChanged()
                                view?.movie_progressBar?.visibility = View.VISIBLE
                                viewModel.searchMovie(it)
                            }, 300)
                        }
                    }
                }
                return false
            }
        })

        searchView?.setOnCloseListener {
            view?.movie_progressBar?.visibility = View.VISIBLE
            viewModel.fetchMovie()
            searchView?.onActionViewCollapsed()
            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuMain_language -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        return super.onOptionsItemSelected(item)
    }
}
