package com.client;

import org.json.JSONObject;

import com.utilities.Date;

public class Ticket {

    private String user_id; 
    private String round_id; 
    private String seat_id; 
    private String booking_time;
    private double price;


    public Ticket(){}
    public Ticket(String user_id, String round_id , String seat_id , double price) {
        this.user_id = user_id;
        this.round_id = round_id;
        this.seat_id = seat_id;
        this.price = price;
    }

    public boolean createTicket(){
        
        Date date = new Date();
        this.booking_time = date.getCurrentTime();

        return true;
    }

    public JSONObject data(){
        
        Date date = new Date();
        this.booking_time = date.getCurrentTime();
        
        JSONObject data = new JSONObject();
        data.put("round_id", this.round_id);
        data.put("user_id", this.user_id);
        data.put("seat_id", this.seat_id);
        data.put("booking_time", this.booking_time);
        data.put("price", this.price);

        return data;
    }



    
}
