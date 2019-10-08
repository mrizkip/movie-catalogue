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
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.repository.LocalRepository
import me.mrizkip.moviecatalogue.ui.common.universalViewModelFactory
import me.mrizkip.moviecatalogue.ui.detailTvShow.DetailTvShowActivity
import me.mrizkip.moviecatalogue.ui.tvShow.TvShowAdapter

class FavoriteTvShowFragment : Fragment() {
    private var tvShowList: ArrayList<TvShow> = arrayListOf()
    private lateinit var adapter: TvShowAdapter

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var localRepository: LocalRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.favoriteTvShow_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        localRepository = LocalRepository(context)
        viewModel = ViewModelProviders.of(this,
            universalViewModelFactory {
                FavoriteViewModel(localRepository)
            }).get(FavoriteViewModel::class.java)

        adapter = TvShowAdapter(context, tvShowList) {
            val intent = Intent(context, DetailTvShowActivity::class.java)
                .putExtra(DetailTvShowActivity.EXTRA_TV_SHOW_ID, it.id)
            startActivity(intent)
        }

        view.favoriteTvShow_recyclerView.adapter = adapter

        getFavoriteTvShows()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavoriteTvShows()
    }

    private fun getFavoriteTvShows() {
        view?.favoriteTvShow_progressBar?.visibility = View.VISIBLE

        viewModel.getFavoriteTvShows().observe(this, Observer { tvShows ->
            tvShows?.let {
                tvShowList.clear()
                if (it.isNotEmpty()) {
                    view?.favoriteTvShow_error?.visibility = View.GONE
                    view?.favoriteTvShow_recyclerView?.visibility = View.VISIBLE
                    tvShowList.addAll(it)
                    adapter.notifyDataSetChanged()
                } else {
                    view?.favoriteTvShow_error?.visibility = View.VISIBLE
                    view?.favoriteTvShow_recyclerView?.visibility = View.GONE
                }
            }
            view?.favoriteTvShow_progressBar?.visibility = View.GONE
        })
    }
}
