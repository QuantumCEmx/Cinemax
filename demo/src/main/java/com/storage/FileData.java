
package com.storage;

public class FileData {

    public final String currentPath = System.getProperty("user.dir");
    public final String fullPath = currentPath+"\\demo\\src\\main\\resources\\";
    //*-----------------------------------------------------------------------
    public final String theater = fullPath+"theaterData.json";
    public final String movie   = fullPath+"movieData.json";
    public final String round   = fullPath+"roundData.json";
    public final String ticket   = fullPath+"ticketData.json";

    public final String member   = fullPath+"memberData.json";

}


