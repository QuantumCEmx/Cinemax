package com.general;

import org.json.JSONObject;

class Seat {
    private String theater_id;
    private int seat_normal;
    private int seat_honeymoon;

    // Constructor
    public Seat(String theater_id, int seat_normal, int seat_honeymoon) {
        this.theater_id = theater_id;
        this.seat_normal = seat_normal;
        this.seat_honeymoon = seat_honeymoon;
    }

    // Method to generate data as JSONObject
    public JSONObject data() {
        JSONObject data = new JSONObject();
        data.put("theater_id", this.theater_id);
        data.put("seat_normal", this.seat_normal);
        data.put("seat_honeymoon", this.seat_honeymoon);

        return data;
    }

    public static void main(String[] args) {
        // Example usage
        Seat seat = new Seat("T001", 30, 20);
        JSONObject jsonData = seat.data();
        System.out.println(jsonData.toString());
    }

}
