package com.table;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.storage.Database;
import com.storage.FileData;

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
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%s %10s" ,"#","");

        for (String key : keys) {System.out.printf("%15s",key);}
        System.out.println("\n-----------------------------------------------------------");

        int length = this.dataSource.length();
        for (int i= 0;i<length;i++){
            
            System.out.printf("%d %10s" ,(i+1),"");
            
            JSONObject item = this.dataSource.getJSONObject(i);
            this.showOneItem(item,keys);
        }

        System.out.println("----------------------------------------------------------");
    }

    private void showOneItem(JSONObject item , String [] keys){
        for (String key:keys) {
            System.out.printf("%15s",item.get(key));
        }

        System.out.println();
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

