package com.client;

import java.util.List;

public class Receipt {

    private String receipt_id;
    private List<Ticket> tickets;
    private double totalAmount;

    public Receipt(){}

    public Receipt(String receipt_id, List<Ticket> tickets) {
        this.receipt_id = receipt_id;
        this.tickets = tickets;
    }
    
    public void  addTicket(Ticket ticket){


    }

    public double calTotal(){

        

        return this.totalAmount;
    }


    
}
