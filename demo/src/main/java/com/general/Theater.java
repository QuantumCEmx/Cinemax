package com.general;

import java.util.Map;

import org.json.JSONObject;

import com.input.Input;

public class Theater {

    private String id = "";
    private String theater_name = "";
    private String seats = "";


    public Map<String, String> schema = Map.of(
            "id", "Auto",
            "theater_name", "String",
            "seats", "String"

    );

    public Theater() {
    }

    public Theater(String id, String theater_name ,String seats) {
        this.id = id;
        this.theater_name = theater_name;

    }

    public JSONObject getData() {
        JSONObject data = new Input().getData(this.schema);
        return data;
    }

    public JSONObject data() {
        JSONObject data = new JSONObject();
        data.put("id", this.id);
        data.put("theater_name", this.theater_name);
        data.put("seats", this.seats);

        return data;
    }

}
