package com.input;

import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import com.storage.Database;
import com.storage.FileData;

public class Input {

    public Input() {}

    public JSONObject getData(Map<String ,String> schemaName) {

        JSONObject obj = new JSONObject();
        Scanner sc = new Scanner(System.in);
        Database db = new Database();

        System.out.println("Enter |");
        try {
            for (String key : schemaName.keySet()) {
                String type = schemaName.get(key);

                if (type.equalsIgnoreCase("String")) {
                    System.out.printf("%s (%s) : ",key,type);
                    String value = sc.nextLine();
                    obj.put(key, value);
                }

                if (type.equalsIgnoreCase("Auto")) {
                    obj.put(key, "auto");
                }
            }

            System.out.println("--------------------");
            System.out.println(obj.toString());
            return obj;

            // db.save(dataSource,file.movie);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;

    }

    public String getId(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID > ");
        String id = sc.nextLine();

        return id;
    }

    public void breakPoint(){
        System.out.println("Press any key to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Continuing...");
    }
}
