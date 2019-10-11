package me.mrizkip.moviecatalogue.ui.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie

class MovieAdapter(
    private val context: Context?,
    private val movieList: ArrayList<Movie>,
    val clickListener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(movieList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(movieList[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(movie: Movie) {
            itemView.itemMovie_tvTitle.text = movie.title
            itemView.itemMovie_tvDescription.text = movie.overview
            itemView.itemMovie_tvReleaseDate.text = movie.releaseDate
            itemView.itemMovie_tvRating.text = movie.voteAverage.toString()
            val userRating = movie.voteAverage!!.toFloat()
            when {
                userRating >= 7 -> itemView.itemMovie_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_good)
                userRating >= 4 && userRating < 7 -> itemView.itemMovie_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_medium)
                userRating < 4 -> itemView.itemMovie_tvRating.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_rating_bad)
            }
            val moviePosterUrl = context?.resources?.getString(R.string.poster_url_small)
            Picasso.get().load("$moviePosterUrl${movie.posterPath}").resize(100, 140).centerCrop()
                .into(itemView.itemMovie_imvPoster)
        }
    }
}