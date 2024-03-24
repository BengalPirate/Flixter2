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
        // Start of network request
        Log.d("MainActivity", "Starting to fetch movies")

        // Use RetrofitInstance.api to make the network request
        RetrofitInstance.api.getTopRatedMovies(BuildConfig.TMDB_API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { movieResponse ->
                        // Successful response
                        Log.d("MainActivity", "Movies fetched successfully: ${movieResponse.results.size} movies received.")
                        moviesAdapter.updateMovies(movieResponse.results)
                    } ?: run {
                        // Null response body
                        Log.d("MainActivity", "Response was successful but the body was null")
                    }
                } else {
                    // Response was not successful
                    Log.d("MainActivity", "Response was not successful: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Network request failed
                Log.e("MainActivity", "Failed to fetch movies", t)
            }
        })
    }
}


