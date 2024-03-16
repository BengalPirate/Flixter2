package com.example.flixter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter.R
import com.example.flixter.Movie

class MoviesAdapter(private val context: Context, private var movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewMoviePoster)
        val titleView: TextView = view.findViewById(R.id.textViewTitle)
        val descriptionView: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        // Set the title and description
        holder.titleView.text = movie.title
        holder.descriptionView.text = movie.overview

        // Load the image
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = movies.size

    // Method to update movies data
    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}
