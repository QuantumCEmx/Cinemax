package com.general;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Movie {
    private String movieId;
    private String movieName;
    private int duration;
    private List<String> genre;
    private String director;

    // Constructor
    public Movie(String movieId, String movieName, int duration, List<String> genre, String director) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.genre = genre;
        this.director = director;
    }

    public JSONObject data(){

        JSONObject data = new JSONObject();
        data.put("movie_id", this.movieId);
        data.put("movie_name", this.movieName);
        data.put("duration", this.duration);
        data.put("genre", new JSONArray(this.genre));
        data.put("director", this.director);

        return data;
    }

}
