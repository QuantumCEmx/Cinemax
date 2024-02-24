package com.general;

import org.json.JSONObject;

public class Round {
    
    private String round_id;
    private String start_time;
    private String theater_id;
    private String movie_id;

    public Round(String round_id, String start_time, String theater_id, String movie_id) {
        this.round_id = round_id;
        this.start_time = start_time;
        this.theater_id = theater_id;
        this.movie_id = movie_id;
    }


     public JSONObject data() {
        JSONObject data = new JSONObject();
        data.put("round_id", this.round_id);
        data.put("start_time", this.start_time);
        data.put("theater_id", this.theater_id);
        data.put("movie_id", this.movie_id);

        return data;
    }

}
