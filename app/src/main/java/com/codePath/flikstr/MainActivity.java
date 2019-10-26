package com.codePath.flikstr;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;

import com.codePath.flikstr.adapters.MovieAdapter;

import com.codePath.flikstr.models.Movie;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;


import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;

import okhttp3.Headers;



public class MainActivity extends AppCompatActivity {


    public static final String NOW_PLAYING_URL =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG   = "MainActivity";

    List<Movie> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // binding = DataBindingUtil.setContentView( this,R.layout.activity_main);
        setContentView(R.layout.activity_main);


       // RecyclerView rvMovies = binding.rvMovies;
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();


        //Create the adapter

        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        // Set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);
        // Set a Layout Manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();


       client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Headers headers, JSON json) {
               JSONObject jsonObject = json.jsonObject;

               try {
                   JSONArray results =  jsonObject.getJSONArray("results");
                   Log.i(TAG, "Results: "+ results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
               } catch (Exception e) {
                   Log.d(TAG, "onSuccess");
                   Log.e(TAG, "Hit json exception");
               }
           }

           @Override
           public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
           }
       });


    }
}
