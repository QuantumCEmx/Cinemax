package com.client;

import org.json.JSONArray;
import org.json.JSONObject;

import com.display.Color;
import com.display.Display;
import com.utilities.Date;

public class Ticket {

    private String user_id;
    private String round_id;
    private String seat_id;
    private String booking_time;
    private double price;

    public String[] memberTypeList = {"","Silver","Gold","Platinum"};
    public String[] colorList = {"","38;5;220m","38;5;49m"};
    public double[] discoutList = {0.00,0.20,0.30,0.50};

    public Ticket() {
    }

    public Ticket(String user_id, String round_id, String seat_id, double price) {
        this.user_id = user_id;
        this.round_id = round_id;
        this.seat_id = seat_id;
        this.price = price;
    }

    public boolean createTicket() {

        Date date = new Date();
        this.booking_time = date.getCurrentTime();

        return true;
    }

    public JSONObject data() {

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

    public void showTicketList(JSONArray tickets, int member_type , String MOVIE_NAME , String THEATER_NAME , String START_TIME ) {
        Display display = new Display();
        double sumPrice = 0;
        double discount = this.discoutList[member_type];
        double discountPrice = 0;

        for (int i = 0; i < tickets.length(); i++) {
            JSONObject ticketItem = tickets.getJSONObject(i);
            sumPrice += Double.parseDouble(ticketItem.getString("price"));


            display.setColor(new Color().GREEN);
            System.out.println("\n*----------------------------------------------*");

            System.err.printf("| [%d]                        \n", i + 1);
            System.out.printf("| Title %10s                  \n", MOVIE_NAME);
            System.out.printf("| Seat-No %10s  : Show Time   \n",
                    ticketItem.getString("row") + "-" + ticketItem.getString("seat"));
            System.out.printf("| Theater %10s  : %s           \n", THEATER_NAME, START_TIME);

            System.out.printf("| Price %.2f                  \n",  Double.parseDouble(ticketItem.getString("price")));
            System.out.println("\n*----------------------------------------------*");
            display.reset();

            
        }

        discountPrice = discount*sumPrice;
        
        System.out.printf("Discount     : %.2f ( %.2f %% ) \n",discountPrice, (discount*100));
        System.out.printf("Total        : %.2f \n", sumPrice);

        sumPrice = sumPrice-discountPrice;
        System.out.printf("Net price    : %.2f \n", sumPrice);

        System.out.println("\n*------------------------------*");

    }

}
