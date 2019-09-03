package me.mrizkip.moviecatalogue.ui.tvShow

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_show.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow

class TvShowFragment : Fragment() {
    private lateinit var tvShowTitles: Array<String>
    private lateinit var tvShowDescriptions: Array<String>
    private lateinit var tvShowReleaseDates: Array<String>
    private lateinit var tvShowRatings: Array<String>
    private lateinit var tvShowPosters: TypedArray
    private lateinit var tvShowSeasons: Array<String>
    private lateinit var tvShowGenres: Array<String>
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

        adapter = TvShowAdapter(context, tvShowList) {
            val intent = Intent(context, DetailTvShowActivity::class.java)
                .putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, it)
            startActivity(intent)
        }

        view.tvShow_recyclerView.adapter = adapter

        prepare()
        addItem()
    }

    private fun prepare() {
        tvShowTitles = resources.getStringArray(R.array.tv_show_titles)
        tvShowDescriptions = resources.getStringArray(R.array.tv_show_descriptions)
        tvShowReleaseDates = resources.getStringArray(R.array.tv_show_release_dates)
        tvShowRatings = resources.getStringArray(R.array.tv_show_rating)
        tvShowPosters = resources.obtainTypedArray(R.array.tv_show_posters)
        tvShowSeasons = resources.getStringArray(R.array.tv_show_seasons)
        tvShowGenres = resources.getStringArray(R.array.tv_show_genre)
    }

    private fun addItem() {
        tvShowList.clear()

        for (i in tvShowTitles.indices) {
            val title = tvShowTitles[i]
            val desc = tvShowDescriptions[i]
            val releaseDate = tvShowReleaseDates[i]
            val rating = tvShowRatings[i]
            val poster = tvShowPosters.getResourceId(i, -1)
            val season = tvShowSeasons[i]
            val genre = tvShowGenres[i]
            val movie = TvShow(title, desc, releaseDate, rating, poster, season, genre)
            tvShowList.add(movie)
        }

        adapter.notifyDataSetChanged()
    }
}
