package com.navigation;

import com.storage.Database;
import com.storage.File;
import com.table.Table;

import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.display.Color;
import com.display.Display;
import com.general.Theater;
import com.input.Input;

public class Selection {

    public final File file = new File();

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
        File file = new File();

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
        File file = new File();

        while (choice > 0) {
            switch (choice) {
                case 1:
                    new Table(pathName);
                    break;
                case 2:
                     String[] fruits = {"#","id", "theater_name" };
                    new Database().get(file.theater ,fruits);
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

    public void movieList (String pathName){
        JSONArray roundData = new Database().get(new File().round);
        JSONArray movieData = new Database().get(new File().movie);
        JSONArray theaterData = new Database().get(new File().theater);

        JSONArray groupData = new JSONArray();
        Display display =   new Display();
        new Display().clear();
        Nav header = new Nav("Movie List");
        header.setEmptyText(false);
        header.renderNavList();

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

            System.out.println("Round ID: " + groupObj.getString("round_id"));
          /*   System.out.println("Theater ID: " + groupObj.getString("theater_id")); */
        
            System.out.println("---------");
            display.setColor(new Color().GREEN);
            JSONObject movieObj = groupObj.getJSONObject("movie");
            if (movieObj != null) {
               /*  System.out.println("Movie ID: " + movieObj.getString("movie_id")); */
                System.out.println("" + movieObj.getString("movie_name"));
                System.out.printf("%s min \n" , movieObj.getString("movie_duration"));
            }

            System.out.println("---------");
        
            display.setColor(new Color().BLUE);
            // Print theater details
            JSONObject theaterObj = groupObj.getJSONObject("theater");
            if (theaterObj != null) {
                System.out.printf("Theater %s | " , theaterObj.getString("theater_name"));
                System.out.printf("%s/%s \n" ,"1", theaterObj.getString("seats"));
            }

            System.out.println("Start Time: " + groupObj.getString("start_time"));
            display.reset();


        }
            System.out.println("\n----------------------------------\n");

      /*       System.out.println("----------------------------------");
            System.out.printf("       | Movie Name : %s \n", movieData.getJSONObject(i).getString("movie_name"));
            System.out.printf("  [%d]  | Duration   : %s minutes\n",i+1, movieData.getJSONObject(i).getString("duration"));
            System.out.printf("       | Seat       : %s/%s\n", "1","30"); */



        Scanner sc = new Scanner(System.in);
        System.out.println("Select Movie");
        System.out.print(" > ");
        sc.nextInt();

    }

}
