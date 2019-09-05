package me.mrizkip.moviecatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tvShow_recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)

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
}
