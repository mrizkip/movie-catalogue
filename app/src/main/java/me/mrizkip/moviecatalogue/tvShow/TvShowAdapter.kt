package me.mrizkip.moviecatalogue.tvShow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv_show.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.TvShow

class TvShowAdapter(
    private val context: Context?,
    private val tvShowList: ArrayList<TvShow>,
    val clickListener: (TvShow) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_tv_show, parent, false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(tvShowList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(tvShowList[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(tvShow: TvShow) {
            itemView.itemTvShow_tvTitle.text = tvShow.title
            itemView.itemTvShow_tvDescription.text = tvShow.description
            itemView.itemTvShow_tvReleaseDate.text = tvShow.releaseDate
            itemView.itemTvShow_tvRating.text = tvShow.userRating
            val userRating = tvShow.userRating.toFloat()
            when {
                userRating >= 7 -> itemView.itemTvShow_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_good)
                userRating >= 4 && userRating < 7 -> itemView.itemTvShow_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_medium)
                userRating < 4 -> itemView.itemTvShow_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_bad)
            }
            Picasso.get().load(tvShow.poster).resize(100, 140).centerCrop()
                .into(itemView.itemTvShow_imvPoster)
            val season = context?.getString(R.string.tv_show_adapter_seasons, tvShow.seasons)
            itemView.itemTvShow_tvSeasons.text = season
            itemView.itemTvShow_tvGenre.text = tvShow.genre
        }
    }
}