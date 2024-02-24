package com.table;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.storage.Database;
import com.storage.File;

public class Table {

    public String targetFile;
    public JSONArray dataSource ;

    public Table(){}

    public Table(String targetFile){

        this.targetFile = targetFile;
        
        this.renderTable();
    }


    public void renderTable(){

        Database db = new Database();

        this.dataSource = db.get(this.targetFile);

        JSONObject jsonObject = this.dataSource.getJSONObject(0);
        String[] keys = JSONObject.getNames(jsonObject);

        // Display Columns name
        System.out.println("------------------------------");
        System.out.print("#" + "\t");
        for (String key : keys) {System.out.print(key + "\t\t");}
        System.out.println("\n------------------------------");

        int length = this.dataSource.length();
        for (int i=1;i<length;i++){
            
            System.out.print(i + "\t");
            JSONObject item = this.dataSource.getJSONObject(i);
            this.showOneItem(item,keys);
        }

        System.out.println("-----------------------------------");
    }

    private void showOneItem(JSONObject item , String [] keys){
        StringBuilder sb = new StringBuilder();
        for (String key:keys) {
            sb.append(item.get(key)+"\t\t");
        }
        System.out.println(sb.toString());
    }

    //add a record to the table
    public boolean addRecord(String fieldName , Object value){
        Database db = new Database();
        File file = new File();
        if (!fieldName.equalsIgnoreCase("id")){
            return false;
        }else {
            int id = Integer.parseInt(value.toString());
            JSONArray dataSource = db.get(file.movie);
            if (isExistId(id,dataSource)){
                return false;
            }else {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("id",id);
                    obj.put("name","null");
                    obj.put("director","null");
                    obj.put("actor","null");
                    obj.put("type","55");
                    obj.put("time","2021-05-31  14:37:48");
                    dataSource.put(obj);

                    db.save(dataSource,file.movie);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }
    /*
     * check whether the id is exist in the json array or not
     */
    private boolean isExistId(int id,JSONArray dataSource){
        for (int i=0;i< dataSource.length();i++){
            if (id == Integer.parseInt((dataSource.get(i).toString()).split(",")[0])){
                return true;
            }
            }
        return false;
    }

}

