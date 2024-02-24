package com.general;

import org.json.JSONObject;

public class Theater {
    
    private String theater_id = "";
    private String theater_name = "";

    public Theater(){}

    public Theater(String theater_id , String theater_name){
        this.theater_id = theater_id;
        this.theater_name = theater_name;

    }

    public JSONObject data(){

        JSONObject data = new JSONObject();
        data.put("theater_id", this.theater_id);
        data.put("theater_name", this.theater_name);

        return data;
    }

    public String getTheater_id() {
        return theater_id;
    }
    public void setTheater_id(String theater_id) {
        this.theater_id = theater_id;
    }
    public String getTheater_name() {
        return theater_name;
    }
    public void setTheater_name(String theater_name) {
        this.theater_name = theater_name;
    }


    
}
