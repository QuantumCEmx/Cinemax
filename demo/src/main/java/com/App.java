package com;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

import com.client.Receipt;
import com.client.Ticket;
import com.general.Movie;
import com.general.Round;
import com.general.Theater;

import com.navigation.Nav;
import com.navigation.NavList;
import com.navigation.Selection;
import com.storage.Database;
import com.storage.File;
import com.table.Table;
import com.utilities.Date;

import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {

        /*
         * File file = new File();
         * Database db = new Database();
         * 
         * Theater theater = new Theater("T-1", "Theater-1");
         * 
         * // *--------------------------------
         * String movieId = "M-1";
         * String movieName = "Harry Potter";
         * int duration = 150;
         * List<String> genre = new ArrayList<>(List.of("Action", "Fantasy"));
         * String director = "Chris Nolan";
         * Movie movie = new Movie(movieId, movieName, duration, genre, director);
         * 
         * // *--------------------------------
         * 
         * String round_id = "R-1";
         * String start_time = "2024-06-01 19:30:00";
         * String theater_id = "T-1";
         * String movie_id = "M-1";
         * Round round = new Round(round_id, start_time, theater_id, movie_id);
         * 
         * // *--------------------------------
         * 
         * JSONObject t2 = db.get(file.tbl_theater, "theater_id", "T-2");
         * 
         * System.out.println(t2.get("theater_id"));
         * System.out.println(t2.get("theater_name"));
         * 
         * Ticket ticket = new Ticket("U-1", "R-1", "A-1", 300);
         * 
         * db.insert(theater.data(), file.tbl_theater);
         * db.insert(movie.data(), file.tbl_movie);
         * db.insert(round.data(), file.tbl_round);
         * db.insert(ticket.data(), file.tbl_ticket);
         */

    

        NavList navList = new NavList();
        
        while (true) {
            Nav adminMenu = new Nav("Cinemax Mannagement", navList.adminMain, 0, 1);

            switch (adminMenu.getChoice()) {
                case 1:
                    new Selection().theater();
                break;
                case 2:
                    new Selection().movie();
                break;
                case 3:
                    new Selection().round();
                break;
                case 4:
                    new Selection().ticket();
                break;
                default:
                   System.exit(0);
                break;
            }
        }

    }

}