
package com.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Database {

    public Database() {
    }

    public void save(JSONArray jsonArray, String pathName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathName)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void insert(JSONObject jsonObject, String pathName, String prefix) {
        try {

            JSONArray jsonArray = readOrCreate(pathName);

            if (jsonArray.length() == 0 && jsonObject.getString("id").equals("auto")) {
                jsonObject.put("id", prefix + "-1");

            } else if (jsonObject.getString("id").equals("auto")) {

                int maxID = this.checkMaxId(pathName);
                jsonObject.put("id", prefix + "-" + Integer.toString(maxID + 1));

            }

            jsonArray.put(jsonObject);

            write(jsonArray, pathName);

            System.out.println("Insert Data successfully to " + pathName);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean update(String pathName, String id, JSONObject newData) {

        JSONObject data = this.get(pathName, "#", id);

        if (data != null) {

            int index = data.getInt("index");

            JSONArray dataList = this.get(pathName);

            dataList.getJSONObject(index);

            newData.put("id", data.get("id"));
            dataList.put(index, newData);

            try {
                this.save(dataList, pathName);
                return true;

            } catch (Exception e) {
                return false;
            }
        }else{
            System.out.println("----------------------");
            System.out.println("No item ID : "+ id);
            System.out.println("----------------------");
            return false;
        }
    }

    public void delete(String pathName, String id) {

        JSONObject found = this.get(pathName, "#", id);

        JSONArray d = get(pathName);

        d.remove(found.getInt("index"));

        try {
            this.save(d, pathName);
        } catch (Exception e) {
        }

        System.out.println(found.toString());

    }

    /* ---------------------------------------- */

    public int checkMaxId(String pathName) {
        JSONArray array = this.get(pathName);

        int maxID = Integer.parseInt(array.getJSONObject(array.length() - 1).get("id").toString().split("-")[1]);
        System.out.println(maxID);
        return maxID;

    }

    public JSONArray get(String pathName) {
        JSONArray jsonArray = null;

        try {

            jsonArray = read(pathName);

            return jsonArray;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public void get(String pathName, String[] keys) {
        try {
            JSONArray jsonArray = read(pathName);
            System.out.println("------------------------");
    
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                for (int j = 0; j < keys.length; j++) {
                    if (keys[j].equals("#")) {
                        System.out.print(i+1);
                    }else{
                        System.out.print(jsonObject.get(keys[j]));
                    }

                    System.out.printf("%10s","");
                }

                System.out.println();
            }
            System.out.println("------------------------");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject get(String pathName, String key, String data) {
        try {
            JSONArray jsonArray = read(pathName);
            int index = Integer.parseInt(data)-1;

            if(key.equals("#")) {
                System.out.println(index);
                JSONObject foundData =  jsonArray.getJSONObject(index);  
                foundData.put("index", index);
                return foundData;
            }else{
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
            
                    if (jsonObject.getInt(key) == index) {
                    JSONObject foundData = jsonObject;
                    foundData.put("index", i);
                    return foundData;
                }
            }
        }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // * Base
    /* --------------------------------------------- */

    private JSONArray readOrCreate(String fileName) throws IOException, JSONException {
        JSONArray jsonArray;
        File file = new File(fileName);

        if (file.exists()) {
            jsonArray = read(fileName);
        } else {
            jsonArray = new JSONArray();
        }
        return jsonArray;

    }

    private JSONArray read(String fileName) throws IOException, JSONException {
        JSONArray jsonArray;
        try (FileReader fileReader = new FileReader(fileName)) {
            int character;
            StringBuilder stringBuilder = new StringBuilder();
            while ((character = fileReader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            jsonArray = new JSONArray(stringBuilder.toString());
        }
        return jsonArray;
    }

    private void write(JSONArray jsonArray, String pathName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathName)) {
            fileWriter.write(jsonArray.toString());
        }
    }
}
