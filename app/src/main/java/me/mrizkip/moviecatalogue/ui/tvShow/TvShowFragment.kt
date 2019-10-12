package me.mrizkip.moviecatalogue.ui.tvShow

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
import kotlinx.android.synthetic.main.fragment_tv_show.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.ui.detailTvShow.DetailTvShowActivity

class TvShowFragment : Fragment() {
    private var tvShowList: ArrayList<TvShow> = arrayListOf()
    private lateinit var adapter: TvShowAdapter

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tvShow_recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel = ViewModelProviders.of(activity!!).get(TvShowViewModel::class.java)

        adapter = TvShowAdapter(context, tvShowList) {
            val intent = Intent(context, DetailTvShowActivity::class.java)
                .putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, it.id)
            startActivity(intent)
        }

        view.tvShow_recyclerView.adapter = adapter

        getTvShowData()
    }

    private fun getTvShowData() {
        tvShowList.clear()

        view?.tvShow_progressBar?.visibility = View.VISIBLE
        viewModel.getStatus().observe(this, Observer { status ->
            if (status) {
                view?.tvShow_error?.visibility = View.GONE
                view?.tvShow_recyclerView?.visibility = View.VISIBLE
            } else {
                view?.tvShow_error?.visibility = View.VISIBLE
                view?.tvShow_recyclerView?.visibility = View.GONE
            }
            view?.tvShow_progressBar?.visibility = View.GONE
        })

        viewModel.getTvShowsData().observe(this, Observer { tvShows ->
            tvShows?.let {
                tvShowList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchMenu = menu.findItem(R.id.menuMain_search)
        val searchManager= activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null

        searchMenu?.let { searchView = it.actionView as SearchView }

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView?.queryHint = "Search Tv Show"

        val handler = Handler()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.equals("")) {
                    tvShowList.clear()
                    adapter.notifyDataSetChanged()
                } else {
                    query?.let {
                        if (it.length > 2) {
                            handler.removeCallbacksAndMessages(null)
                            handler.postDelayed({
                                tvShowList.clear()
                                adapter.notifyDataSetChanged()
                                view?.tvShow_progressBar?.visibility = View.VISIBLE
                                viewModel.searchTvShow(it)
                            }, 300)
                        }
                    }
                }
                return false
            }
        })

        searchView?.setOnCloseListener {
            view?.tvShow_progressBar?.visibility = View.VISIBLE
            viewModel.fetchTvShows()
            searchView?.onActionViewCollapsed()
            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuMain_language -> startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        return super.onOptionsItemSelected(item)
    }
}
