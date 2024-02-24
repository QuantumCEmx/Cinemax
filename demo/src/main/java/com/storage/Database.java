
package com.storage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Database {
    
    public Database() {}


    public void save(JSONArray jsonArray, String pathName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathName)) {
            fileWriter.write(jsonArray.toString());
        }catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

    public void insert(JSONObject jsonObject, String pathName) {
        try {
            JSONArray jsonArray = readOrCreate(pathName);
            
            jsonArray.put(jsonObject);
            
            write(jsonArray, pathName);
            
            System.out.println("Insert Data successfully to " + pathName);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

/*     public void get(String pathName) {
        try {
            JSONArray jsonArray = read(pathName);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject.toString(4)); // Indent factor is set to 4 for better readability
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    } */

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
    
    public void get(String pathName , String key){
        try {
            JSONArray jsonArray = read(pathName);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject.get(key)); // Indent factor is set to 4 for better readability
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject get(String pathName , String key , String data){
        try {
            JSONArray jsonArray = read(pathName);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.get(key).equals(data)){
                    JSONObject foundData = jsonObject;  
                    return foundData;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    
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
