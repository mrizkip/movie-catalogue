package me.mrizkip.moviecatalogue.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context, private val movieList: ArrayList<Movie>, val clickListener: (Movie) -> Unit) :
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(movieList[position])


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val imvPoster: ImageView = itemView.itemMovie_imvPoster
//        private val tvTitle: TextView = itemView.findViewById(R.id.itemMovie_tvTitle)
//        private val tvDesc: TextView = itemView.findViewById(R.id.itemMovie_tvDescription)
//        private val tvDate: TextView = itemView.findViewById(R.id.itemMovie_tvReleaseDate)

        fun bindItem(movie: Movie) {
            itemView.itemMovie_tvTitle.text = movie.title
            itemView.itemMovie_tvDescription.text = movie.description
            itemView.itemMovie_tvReleaseDate.text = movie.releaseDate
            Picasso.get().load(movie.poster).resize(100, 140).centerCrop().into(itemView.itemMovie_imvPoster)
        }
    }
}