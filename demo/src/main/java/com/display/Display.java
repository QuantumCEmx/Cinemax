package com.display;

public class Display {

    public void setColor(String color){

        System.out.printf("\033[%s",color);
    }
    

    public void setColor(Color color){

        System.out.printf("%s",color);
    }

    public void reset(){
        System.out.printf("\033[0m");
    }

    public void clear(){
       System.out.print("\033[H\033[2J");
    }
}
