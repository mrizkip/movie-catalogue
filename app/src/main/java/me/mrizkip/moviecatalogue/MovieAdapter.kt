package me.mrizkip.moviecatalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

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
        private val moviePoster: ImageView = itemView.findViewById(R.id.itemMovie_imvPoster)
        private val movieTitle: TextView = itemView.findViewById(R.id.itemMovie_tvTitle)
        private val movieDesc: TextView = itemView.findViewById(R.id.itemMovie_tvDescription)
        private val movieDate: TextView = itemView.findViewById(R.id.itemMovie_tvReleaseDate)

        fun bindItem(movie: Movie) {
            movieTitle.text = movie.title
            movieDesc.text = movie.description
            movieDate.text = movie.releaseDate
            Picasso.get().load(movie.poster).resize(100,140).centerCrop().into(moviePoster)
        }
    }
}