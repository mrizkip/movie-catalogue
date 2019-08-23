package me.mrizkip.moviecatalogue.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.Movie

class MovieAdapter(private val context: Context) :
    BaseAdapter() {

    var movies: ArrayList<Movie> = arrayListOf()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        val movie = getItem(position) as Movie
        viewHolder.bindItem(movie)
        return view
    }

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size

    private class ViewHolder(itemView: View) {
        private val imvPoster: ImageView = itemView.findViewById(R.id.itemMovie_imvPoster)
        private val tvTitle: TextView = itemView.findViewById(R.id.itemMovie_tvTitle)
        private val tvDesc: TextView = itemView.findViewById(R.id.itemMovie_tvDescription)
        private val tvDate: TextView = itemView.findViewById(R.id.itemMovie_tvReleaseDate)

        fun bindItem(movie: Movie) {
            tvTitle.text = movie.title
            tvDesc.text = movie.description
            tvDate.text = movie.releaseDate
            Picasso.get().load(movie.poster).resize(100, 140).centerCrop().into(imvPoster)
        }
    }
}