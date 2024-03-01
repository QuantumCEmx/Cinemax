package com.client;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.input.Input;
import com.login.Validation;
import com.storage.Database;
import com.storage.FileData;

public class Member {
    

    public JSONObject checkMember(){

        Scanner str = new Scanner(System.in);
        FileData file = new FileData();

        System.out.println("Enter Your id");
        System.out.print(" > ");
        String id = str.nextLine();

        if(!Validation.getCheckCharAndMaxnum(id, 13)){
            System.out.println(" Id Card Fail");
            new Input().breakPoint();
            return null;
        }

        Database data = new Database();

        JSONArray memberData = data.get(file.member);
        JSONObject foundData = new JSONObject();

        for (int i = 0; i < memberData.length(); i++) {
            JSONObject memberItem = memberData.getJSONObject(i);
            if(memberItem.getString("id").equals(id)){
                foundData = memberItem;
                foundData.put("index", i);
            }
        }

        if(!foundData.isEmpty()){
            //System.out.println(foundData);
            //System.out.println("Found");
            return foundData;
        }else{
            System.out.println("No member was found with id " + id);
            new Input().breakPoint();
            return  null;
        }
    }
}
