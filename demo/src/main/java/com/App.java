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


        /* Test */

        File file = new File();
        NavList navList = new NavList();

     /*    while (true) {
            Nav adminMenu = new Nav("Cinemax Mannagement", navList.adminMain, 0, 1);


            switch (adminMenu.getChoice()) {
                case 1:
                    new Selection(new Theater().schema,"Theater",file.theater, "TH");
                break;
                case 2:
                    new Selection(new Movie().schema,"Movie",file.movie, "MO");
                break;
                case 3:
                    new Selection().round(new Round().schema,file.round, "RO");
                break;
                case 4:
                    new Selection(new Theater().schema,"Ticket",file.ticket, "TK");
                break;
                case 5:
                    new Selection(new Theater().schema,"Receipt",file.theater, "RE");
                break;
                default:
                   System.exit(0);
                break;
            }
        } */

             while (true) {
            Nav adminMenu = new Nav("Cinemax Ticket", navList.clientMain, 0, 1);


            switch (adminMenu.getChoice()) {
                case 1:
                    new Selection().movieList(file.movie);;
                break;
                case 2:
                    new Selection(new Movie().schema,"Movie",file.movie, "MO");
                break;
                default:
                   System.exit(0);
                break;
            }
        }

        

    }
}



   