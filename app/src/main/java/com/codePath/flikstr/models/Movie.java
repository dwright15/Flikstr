package com.codePath.flikstr.models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    int movieId;

    // empty constructor needed by the parceler library
    public Movie(){}


    public Movie(JSONObject jsonObject) throws Exception {
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.posterPath = jsonObject.getString("poster_path");
        this.title  = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.rating = jsonObject.getDouble("vote_average");
        this.movieId  = jsonObject.getInt("id");
    }


    public static List<Movie> fromJsonArray(JSONArray jsonArray) throws Exception{
        List<Movie> movies = new ArrayList<>();
        for(int i =0 ; i< jsonArray.length(); i++)
            movies.add(new Movie(jsonArray.getJSONObject(i)));

    return movies;
    }

    public int getMovieId() {
        return movieId;
    }

    public double getRating() {
        return rating;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342/"+ posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() { return "https://image.tmdb.org/t/p/w342/" + backdropPath; }
}
