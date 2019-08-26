package me.mrizkip.moviecatalogue.tvShow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_show.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow
import me.mrizkip.moviecatalogue.model.TvShowsData

class TvShowFragment : Fragment() {
    private var tvShowList: ArrayList<TvShow> = arrayListOf()
    private lateinit var adapter: TvShowAdapter

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

        tvShowList.addAll(TvShowsData.listData)
        adapter = TvShowAdapter(context, tvShowList) {
            val intent = Intent(context, DetailTvShowActivity::class.java)
                .putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, it)
            startActivity(intent)
        }

        view.tvShow_recyclerView.adapter = adapter
    }
}
