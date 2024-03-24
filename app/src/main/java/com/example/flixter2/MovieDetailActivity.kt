package com.example.flixter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flixter2.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.flixter2.R
import com.example.flixter2.Movie

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Retrieve data passed from the previous activity
        val movieTitle = intent.getStringExtra("EXTRA_MOVIE_TITLE")
        val movieOverview = intent.getStringExtra("EXTRA_MOVIE_OVERVIEW")
        val moviePosterUrl = intent.getStringExtra("EXTRA_MOVIE_POSTER_URL") // Example for image

        // Example of setting up TextViews
        val titleTextView: TextView = findViewById(R.id.movieTitleTextView)
        val overviewTextView: TextView = findViewById(R.id.movieOverviewTextView)
        // Assuming you have an ImageView for the poster
        val posterImageView: ImageView = findViewById(R.id.moviePosterImageView)


        titleTextView.text = movieTitle
        overviewTextView.text = movieOverview

        // If you're using Glide to load an image into an ImageView
        Glide.with(this)
            .load(moviePosterUrl)
            .into(posterImageView)
    }
}
