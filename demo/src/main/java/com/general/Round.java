package com.general;

import java.util.Map;

import org.json.JSONObject;

import com.input.Input;

public class Round {
    
    private String id;
    private String start_time;
    private String theater_id;
    private String movie_id;

    public Map<String, String> schema = Map.of(
        "id", "Auto",
        "start_time", "String",
        "theater_id", "String",
        "movie_id", "String"
    );

    public Round(){}
    
    public Round(String id, String start_time, String theater_id, String movie_id) {
        this.id = id;
        this.start_time = start_time;
        this.theater_id = theater_id;
        this.movie_id = movie_id;
    }

    public JSONObject getData() {
        JSONObject data = new Input().getData(this.schema);
        return data;
    }

     public JSONObject data() {
        JSONObject data = new JSONObject();
        data.put("id", this.id);
        data.put("start_time", this.start_time);
        data.put("theater_id", this.theater_id);
        data.put("movie_id", this.movie_id);

        return data;
    }

}
