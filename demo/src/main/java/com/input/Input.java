package com.input;

import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import com.model.Schema;
import com.storage.Database;
import com.storage.File;

public class Input {

    public Input() {
    }

    public JSONObject getData(Map<String ,String> schemaName) {

        Database db = new Database();
        JSONObject obj = new JSONObject();

        Scanner sc = new Scanner(System.in);

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
                    obj.put(key, "Test-auto");
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
}
