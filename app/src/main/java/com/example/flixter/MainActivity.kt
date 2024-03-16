package com.example.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flixter.BuildConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        moviesRecyclerView = findViewById(R.id.recyclerViewMovies) // Make sure you have a RecyclerView with this ID in your layout
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter with empty list or a data source
        moviesAdapter = MoviesAdapter(this, listOf()) // Start with an empty list or pre-populated data
        moviesRecyclerView.adapter = moviesAdapter

        // Make a network request to fetch movies
        fetchMovies()
    }

    private fun fetchMovies() {
        // Use RetrofitInstance.api to make the network request
        RetrofitInstance.api.getNowPlayingMovies(BuildConfig.TMDB_API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        moviesAdapter.updateMovies(movieResponse.results)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}


