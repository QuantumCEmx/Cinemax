package com.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.display.Color;
import com.display.Display;

public class Nav {

    private String header;
    private List<String> navItems = new ArrayList<>();

    private int choice = 0;
    private int startIndex = 0;
    private boolean clear = true;
    private int padding = 10;
    private boolean emptyText = true;

    private Display display = new Display();

    public Nav(
            String header,
            List<String> navItems,
            int choice,
            int startIndex,
            boolean clear,
            int padding
        ) {

        this.header = header;
        this.navItems = navItems;
        this.choice = choice;
        this.startIndex = startIndex;
        this.clear = clear;
        this.padding = padding;

        this.renderNavList();

    }


    public Nav(
        String header,
        List<String> navItems,
        int choice,
        int startIndex
        ) {

        this.header = header;
        this.navItems = navItems;
        this.choice = choice;
        this.startIndex = startIndex;


        this.renderNavList();

        }

    public Nav(
            String header) {

        this.header = header;
        this.renderNavList();

    }

    public void printLine() {
        int headerLength = this.header.length() + this.padding;

        for (int i = 0; i < headerLength; i++) {
            System.out.print("=");
        }

        System.out.print("\n");

    }

    public void printHeader() {
        int headerLength = this.header.length();
        int paddingLength = this.padding / 2;

        printLine();
        System.out.printf("%" + (paddingLength + headerLength) + "s%n", this.header);
        printLine();

    }



    public void renderNavList() {

        
        if (this.clear) display.clear();

        display.setColor(new Color().GREEN);
        printHeader();
        display.reset();

        if (!this.navItems.isEmpty()) {

            int i = this.startIndex;

            for (String item : this.navItems) {
                System.out.printf("[%d] : %s \n", i, item);

                i++;
            }

        } else {
            if(this.emptyText){

                System.out.println("[-] No items to show.");
            }
        }
    }

    public int getChoice() {

        Scanner sc = new Scanner(System.in);
        System.out.print(" > ");

        this.choice = sc.nextInt();

     
        return this.choice;
    }

    public void setEmptyText(boolean isShown){
        this.emptyText = isShown;
    } 

}
