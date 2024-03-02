package com.navigation;

import com.storage.Database;
import com.storage.FileData;
import com.table.Table;
import com.validattion.ValidationData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import javax.xml.crypto.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import com.client.Member;
import com.client.Ticket;
import com.display.Color;
import com.display.Display;
import com.general.GenerateQRCode;
import com.general.Movie;
import com.general.Theater;
import com.input.Input;
import com.login.CreateAccount;
import com.login.Validation;


public class Selection {

    public String[] memberTypeList = {"Silver","Gold","Platinum"};
    public String[] colorList = {"","38;5;220m","38;5;49m"};
    public double[] discoutList = {0.20,0.30,0.50};


    public final FileData file = new FileData();

    public Selection() {
    }

    public Selection(Map<String, String> schema, String header, String pathName, String prefix) {

        this.SelectionLCRUD(schema, header, pathName, prefix);

    }

    public void SelectionLCRUD(Map<String, String> schema, String header, String pathName, String prefix) {
        Nav selector = new Nav(header, new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();
        Input input = new Input();
        
        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(pathName);
                    break;
                case 2:
                    new Database().insert(new Input().getData(schema), pathName, prefix);
                    break;
                case 3:
                    String id = input.getId();

                    new Database().update(pathName, id, new Input().getData(schema));
                    break;
                case 4:
                    new Database().delete(pathName, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }
    }

    public void theater() {

        Nav selector = new Nav("Theater", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();
        FileData file = new FileData();
        Input input = new Input();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.theater);
                    break;
                case 2:
                    new Database().insert(new Theater().getData(), file.theater, "TH");
                    break;
                case 3:
                    String id = input.getId();

                    new Database().update(file.theater, id, new Theater().getData());
                    break;
                case 4:
                    new Database().delete(file.theater, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void movie() {

        Nav selector = new Nav("Movie", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.movie);
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void round(Map<String, String> schema, String pathName, String prefix) {

        Nav selector = new Nav("Round", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();
        Input input = new Input();
        FileData file = new FileData();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(pathName);
                    break;
                case 2:
                    String[] cols = { "#", "id", "theater_name" };
                    new Database().get(file.theater, cols);
                    new Database().insert(new Input().getData(schema), pathName, prefix);
                    break;
                case 3:
                    String id = input.getId();
                    new Database().update(pathName, id, new Input().getData(schema));
                    break;
                case 4:
                    new Database().delete(pathName, input.getId());
                    break;
                default:
                    break;
            }

            choice = selector.getChoice();
        }

    }

    public void ticket() {

        Nav selector = new Nav("Ticket", new NavList().LCRUD, 1, 1);
        int choice = selector.getChoice();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(file.ticket);
                    break;

                default:
                    break;
            }

            choice = selector.getChoice();
        }
    }

    public void movieList(String pathName) {
        JSONArray roundData = new Database().get(new FileData().round);
        JSONArray movieData = new Database().get(new FileData().movie);
        JSONArray theaterData = new Database().get(new FileData().theater);

        JSONArray groupData = new JSONArray();
        Display display = new Display();
        new Display().clear();
        Nav header = new Nav("Movie List");
        header.setEmptyText(false);
        header.renderNavList();

        JSONObject ticketTempData = new JSONObject();
        JSONArray ticketTempDataSet = new JSONArray();

        JSONArray ticketTempSet = new JSONArray();
        JSONArray booked = new JSONArray();

        for (int i = 0; i < roundData.length(); i++) {
            JSONObject roundItem = roundData.getJSONObject(i);
            String roundId = roundItem.getString("id");

            JSONObject groupObj = new JSONObject();
            groupObj.put("round_id", roundId);

            groupObj.put("start_time", roundItem.getString("start_time"));
            groupObj.put("theater_id", roundItem.getString("theater_id"));
            groupObj.put("movie_id", roundItem.getString("movie_id"));

            for (int j = 0; j < movieData.length(); j++) {
                JSONObject movieItem = movieData.getJSONObject(j);
                if (roundItem.getString("movie_id").equals(movieItem.getString("id"))) {
                    JSONObject movieObj = new JSONObject();
                    movieObj.put("movie_id", movieItem.getString("id"));
                    movieObj.put("movie_name", movieItem.getString("movie_name"));
                    movieObj.put("movie_duration", movieItem.getString("duration"));
                    groupObj.put("movie", movieObj);
                    break;
                }
            }
            for (int j = 0; j < theaterData.length(); j++) {
                JSONObject theaterItem = theaterData.getJSONObject(j);
                if (roundItem.getString("theater_id").equals(theaterItem.getString("id"))) {
                    JSONObject theaterObj = new JSONObject();
                    theaterObj.put("theater_id", theaterItem.getString("id"));
                    theaterObj.put("theater_name", theaterItem.getString("theater_name"));
                    theaterObj.put("seats", theaterItem.getString("seats"));
                    groupObj.put("theater", theaterObj);
                    break;
                }
            }

            groupData.put(groupObj);
        }

        for (int i = 0; i < groupData.length(); i++) {
            JSONObject groupObj = groupData.getJSONObject(i);
            System.out.println("\n----------------------------------\n");

            display.setColor(new Color().YELLOW);
            System.out.printf("[%d]: \n", (i + 1));
            System.out.println("Round ID: " + groupObj.getString("round_id"));
            /* System.out.println("Theater ID: " + groupObj.getString("theater_id")); */

            System.out.println("---------");
            display.setColor(new Color().GREEN);
            JSONObject movieObj = groupObj.getJSONObject("movie");
            if (movieObj != null) {
                /* System.out.println("Movie ID: " + movieObj.getString("movie_id")); */
                System.out.println("" + movieObj.getString("movie_name"));
                System.out.printf("Duration %s min \n", movieObj.getString("movie_duration"));
            }

            System.out.println("---------");

            display.setColor(new Color().BLUE);
            JSONObject theaterObj = groupObj.getJSONObject("theater");
            if (theaterObj != null) {
                int sumSeats = new Theater().calTickets(groupObj.getString("round_id"));
                System.out.printf("Theater %s | ", theaterObj.getString("theater_name"));
                System.out.printf("%d/%s \n", sumSeats, theaterObj.getString("seats"));
            }

            System.out.println("Start Time: " + groupObj.getString("start_time"));
            display.reset();

        }
        System.out.println("\n----------------------------------\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("Select Movie");
        int choice ;
        // header.getChoices();
        JSONObject selectedRound;
        
        // while (true) {
        //     if (choice - 1 >= 0 && choice - 1 < groupData.length() ) {
                
        //         selectedRound = groupData.getJSONObject(choice - 1);
        //         // System.out.println(groupData.getJSONObject(choice-1));
        //         break;
        //     } else {
        //         System.out.print(" > ");
        //         choice = sc.nextInt();

        //     }
        // }
        while (true) {
            System.out.print(" > ");
    
            if (Validation.CheckOlyNum(sc)) {
                choice = sc.nextInt();
                if (choice - 1 >= 0 && choice - 1 < groupData.length() ) {
                    selectedRound = groupData.getJSONObject(choice - 1);
                    // System.out.println(groupData.getJSONObject(choice-1));
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid numeric choice.");
                    // sc.nextLine(); 
    
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numeric choice.");
                sc.nextLine(); 
            }
        }

        // * Select Seat */
        // * ================================================================ */

        while (true) {
            System.out.println(selectedRound);
            String USER_TEMP_ID = "US-1";
            String ROUND_ID = selectedRound.getString("round_id");
            String MOVIE_NAME = selectedRound.getJSONObject("movie").getString("movie_name");
            String THEATER_NAME = selectedRound.getJSONObject("theater").getString("theater_name");
            String START_TIME = selectedRound.getString("start_time");
            String SEATS = selectedRound.getJSONObject("theater").getString("seats");



            display.clear();
            System.out.println("\n----------------------------------\n");

            display.setColor(new Color().YELLOW);
            System.out.println("Round ID: " + selectedRound.getString("round_id"));
            /*
             * System.out.println("Theater ID: " + selectedRound.getString("theater_id"));
             */

            System.out.println("---------");
            display.setColor(new Color().GREEN);
            JSONObject movieObj = selectedRound.getJSONObject("movie");
            if (movieObj != null) {
                /* System.out.println("Movie ID: " + movieObj.getString("movie_id")); */
                System.out.println("" + movieObj.getString("movie_name"));
                System.out.printf("Duration %s min \n", movieObj.getString("movie_duration"));
            }

            System.out.println("---------");

            display.setColor(new Color().BLUE);
            JSONObject theaterObj = selectedRound.getJSONObject("theater");
            if (theaterObj != null) {
                 int sumSeats = new Theater().calTickets(ROUND_ID);
                System.out.printf("Theater %s | ", theaterObj.getString("theater_name"));
                System.out.printf("%d/%s \n", sumSeats, theaterObj.getString("seats"));
            }

            System.out.println("Start Time: " + selectedRound.getString("start_time"));
            display.reset();

            System.out.println(" > ");

            // * Render Seat */
            // *==============================================================================
            // */
            JSONArray ticketRealData = new Database().get(new FileData().ticket);

            /* System.out.println(ticketTempData.toString(4)); */

            if (!ticketTempData.isEmpty()) {

                ticketRealData.put(ticketTempData);

            }

            System.out.println("-------------------------------");
            /* System.out.println(ticketRealData.toString(4)); */

            for (int i = 0; i < ticketRealData.length(); i++) {

                JSONObject jsonObject = ticketRealData.getJSONObject(i);

                JSONArray ticketArray = jsonObject.getJSONArray("ticket");
                String paid = jsonObject.getString("paid");
                String id = jsonObject.getString("id");
                String round_id = jsonObject.getString("round_id");
                String user_id = jsonObject.getString("user_id");

                if (round_id.equals(ROUND_ID)) {

                    for (int j = 0; j < ticketArray.length(); j++) {

                        JSONObject ticketObject = ticketArray.getJSONObject(j);
                        String row = ticketObject.getString("row");
                        String seatNumber = ticketObject.getString("seat");
                        String status = ticketObject.getString("status");

                        /* System.out.println("row"+ticketObject); */

                        ticketObject.put("row", row);
                        ticketObject.put("seat", seatNumber);

                        ticketObject.put("paid", paid);
                        ticketObject.put("round_id", round_id);
                        ticketObject.put("user_id", user_id);

                        booked.put(ticketObject);

                    }

                    // Print other information
                    /*
                     * System.out.println("Paid: " + paid);
                     * System.out.println("ID: " + id);
                     * System.out.println("User_id: " + user_id);
                     * System.out.println();
                     */

                }

            }

            System.out.println("-------------------------");
            /* System.out.println(booked.toString(4)); */

            char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
            int row = 0;
            int col = 0;
            for (int i = 1; i <= Integer.parseInt(theaterObj.getString("seats")); i++) {

                if (i % 10 == 1 && i > 0) {
                    System.out.printf("%s ", alpha[row]);
                    col = 0;
                }

                for (int k = 0; k < booked.length(); k++) {
                    JSONObject bookItem = booked.getJSONObject(k);
                    if (bookItem.getString("row").equals(String.valueOf(alpha[row])) &&
                            bookItem.getString("seat")
                                    .equals(("0" + (col + 1)).substring(Math.max(("0" + (col + 1)).length() - 2, 0)))

                    ) {

                        if (Integer.parseInt(bookItem.getString("paid")) > 0) {
                            new Display().setColor(new Color().GREEN);
                        } else {
                            new Display().setColor(new Color().YELLOW);
                        }

                        /*
                         * if (bookItem.getString("user_id").equals(USER_TEMP_ID)) {
                         * 
                         * new Display().setColor(new Color().YELLOW);
                         * }
                         */

                    }
                }

                System.out.printf("[%s] ", ("0" + (col + 1)).substring(Math.max(("0" + (col + 1)).length() - 2, 0)));

                new Display().reset();

                if (i % 10 == 0 && i > 0) {
                    System.out.println("");
                    row++;
                }
                col++;
            }

            System.out.println("");
            System.out.println("");

            System.out.println("-------------------Screen------------------");

            // *==============================================================================
            // */

            System.out.println();
            System.out.println();
            // System.out.println(ticketTempData.toString(4));

            Nav seatAction = new Nav("Select", new NavList().saatAction, 1, 1, false, 10);
            int choiceSeat = seatAction.getChoice();

            if (choiceSeat == 2) {

                if (!ticketTempData.isEmpty()) {
                    
                    JSONArray tickets = ticketTempData.getJSONArray("ticket");

                    new Ticket().showTicketList(tickets ,0, MOVIE_NAME, THEATER_NAME, START_TIME);
                    
                    Nav selector = new Nav("Do you have Member Card ?", new NavList().confirmSeat, 1, 1, false, 10);
                    int confirm = selector.getChoice();
                    JSONObject foundData = new JSONObject();

                    if(confirm == 1){

                        Member member = new Member();
                        
                        Nav headerMember = new Nav("Member",false);
                        headerMember.setEmptyText(false);
                        headerMember.setClear(false);
                        headerMember.renderNavList();
                        
                        foundData = member.checkMember();
                        
                    }

                    if( !foundData.isEmpty() ){
                        new Ticket().showTicketList(tickets, Integer.parseInt(foundData.getString("member_type")) , MOVIE_NAME, THEATER_NAME, START_TIME);   
                    }else{
                        new Ticket().showTicketList(tickets, 0, MOVIE_NAME, THEATER_NAME, START_TIME);   
                    }

                    Nav PaymentNav = new Nav("Payment Method", new NavList().payment, 1, 1, false, 10);

                    int paymentMethod = PaymentNav.getChoice();
                    if (paymentMethod > 0) {
                        ticketTempData.put("paid", String.valueOf(paymentMethod));
                        
                        if(!foundData.isEmpty()){
                           ticketTempData.put("user_id", foundData.get("id"));
                        }
                        
                        new Database().insert(ticketTempData, file.ticket, "TK");

                        display.setColor(new Color().GREEN);
                        System.out.println("\n Your Ticket has been booked successfully!");
                        display.setColor(new Color().BLUE);
                        System.out.print("Please show this to the cashier at the cinema.\n");

                        display.reset();

                        GenerateQRCode qr = new GenerateQRCode();

                        JSONArray ticketsQR = ticketTempData.getJSONArray("ticket");
                        System.out.println(ticketsQR);

                        for (int i = 0; i < ticketsQR.length(); i++) {
                            JSONObject ticketItem = ticketsQR.getJSONObject(i);
                            try {
                                String qString = " Seat : "+
                                 ticketItem.getString("row")+"-"+ticketItem.getString("seat") + 
                                 "\n Paid : Success"+
                                 "\n Theater : "+ THEATER_NAME+
                                 "\n Movie : "+ MOVIE_NAME+
                                 "\n Start Time : "+ START_TIME




                                 
                                 ;
                                qr.generateQrCode(qString, ticketItem.getString("row")+"-"+ticketItem.getString("seat"));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        new Input().breakPoint();
                    }

                    break;
                } else {
                    System.out.println("Please Select a Seat Before  Proceeding to Pay!");
                    break;

                }
            }

            if (choiceSeat == 3)
                break;

            System.out.print("Enter row");
            System.out.print(" > ");
            String choice_row = sc.next();
            choice_row = choice_row.toUpperCase();
            System.out.print("Enter Seat number");
            System.out.print(" > ");
            String choice_seat = sc.next();

            boolean bookAble = true;
            int alphaIndex =  new ValidationData().alphabets(choice_row.charAt(0))+1;

            // System.out.println(alphaIndex);

            if (
                (Integer.parseInt(choice_seat) <= 10 && Integer.parseInt(choice_seat) > 0)  && 
                choice_row.length() == 1 && 
                (alphaIndex <= Integer.parseInt(SEATS)/10 && alphaIndex > 0) && 
                !choice_row.equals("0") && 
                !choice_seat.equals("0")
            ) {

                Nav selector = new Nav("Confirm", new NavList().confirmSeat, 1, 1, false, 10);
                int confirm = selector.getChoice();

                if (confirm == 1) {

                    for (int k = 0; k < booked.length(); k++) {
                        JSONObject bookItem = booked.getJSONObject(k);
                        if (bookItem.getString("row").equals(choice_row) &&
                                bookItem.getString("seat")
                                        .equals(choice_seat.length() == 2 ? choice_seat : "0" + choice_seat)

                        ) {
                            display.setColor(new Color().RED);
                            System.err.println("\nThis seat is already Booked!\nPlease choose another one.\n\n");
                            display.reset();
                            bookAble = false;
                            break;
                        }
                    }

                    if (bookAble) {

                        display.setColor(new Color().GREEN);
                        System.out.println("\n-------------------------------");
                        System.err.println("Booked [/]");
                        System.out.printf("| Title %s   | \n", MOVIE_NAME);
                        System.out.printf("| Seat-No %s | Show Time\n", choice_row + "-" + choice_seat);
                        System.out.printf("| Theater %s   | ", THEATER_NAME);
                        System.out.printf("%s ", START_TIME);
                        System.out.println("\n-------------------------------");
                        System.out.println();
                        System.out.println();

                        display.reset();

                        JSONObject reservation = new JSONObject();

                        reservation.put("row", choice_row);
                        reservation.put("seat", choice_seat.length() == 2 ? choice_seat : "0" + choice_seat);
                        reservation.put("status", "reserved");
                        reservation.put("price", "200");
                        ticketTempSet.put(reservation);

                        ticketTempData.put("id", "auto");
                        ticketTempData.put("round_id", ROUND_ID);
                        ticketTempData.put("ticket", ticketTempSet);
                        ticketTempData.put("user_id", "US-1");
                        ticketTempData.put("paid", "0");

                    }

                    new Input().breakPoint();

                } else {
                    break;
                }
            }else{
                display.setColor(new Color().RED);
                System.out.println("\n-------------------------------");
                System.out.println("Invalid value, Can't book seat");
                System.out.println("-------------------------------");
                display.reset();
                new Input().breakPoint();
            }
        }

    }

    public void member() {

        Nav header = new Nav("New Member Register");
        header.setEmptyText(false);
        header.renderNavList();

        new CreateAccount();

        new Input().breakPoint();
    }

    public void memberType() {
        Display display = new Display();

        Member member = new Member();
        
        Nav header = new Nav("Member Subscription");
        header.setEmptyText(false);
        header.renderNavList();
        
        JSONObject foundData = member.checkMember();

        if(foundData != null){

            Nav memberType = new Nav("Member Type");
            memberType.setEmptyText(false);
            memberType.renderNavList();
            
        System.out.println("[1] Silver      | 1000/year | discount : 20%");
        
        display.setColor("38;5;220m");
        System.out.println("[2] Gold        | 1300/year | discount : 30%");

        display.setColor("38;5;49m");
        System.out.println("[3] Platinum    | 1500/year | discount : 50%");

        display.reset();
        System.out.println("\n");
        System.out.println("Select Member Type");
        display.setColor("38;5;74m");
        int selectedMemberType =  memberType.getChoice();
        display.reset();

        foundData.put("member_type",String.valueOf(selectedMemberType));

        System.out.printf("You're selected \033[%s%s \n",colorList[selectedMemberType-1],memberTypeList[selectedMemberType-1]);
        display.reset();

        new Database().updateMemberType(file.member, foundData.getString("id"), foundData);
        
        new Input().breakPoint();
        }
    }

}
