package com.general;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.input.Input;
import com.storage.Database;
import com.storage.FileData;

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

    public int calTickets(String round_id){
        JSONArray tickets = new Database().get(new FileData().ticket);
        int sumT = 0;
        
        for (int i = 0; i < tickets.length(); i++) {
            JSONObject ticketItem = tickets.getJSONObject(i);
            if(ticketItem.getString("round_id").equals(round_id)){

                sumT += ticketItem.getJSONArray("ticket").length();
            }
        }

        System.out.println("sum "+sumT);
        return sumT;
    }

}
