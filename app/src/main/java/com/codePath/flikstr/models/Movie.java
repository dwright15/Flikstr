package com.codePath.flikstr.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject jsonObject) throws Exception {
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.posterPath = jsonObject.getString("poster_path");
        this.title  = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
    }


    public static List<Movie> fromJsonArray(JSONArray jsonArray) throws Exception{
        List<Movie> movies = new ArrayList<>();
        for(int i =0 ; i< jsonArray.length(); i++)
            movies.add(new Movie(jsonArray.getJSONObject(i)));

    return movies;
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

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/" + backdropPath;
    }
}
