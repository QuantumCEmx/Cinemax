package com.general;

import org.json.JSONObject;

import com.input.Input;
import com.model.Schema;

public class Theater {
    
    private String theater_id = "";
    private String theater_name = "";

    public Theater(){}

    public Theater(String theater_id , String theater_name){
        this.theater_id = theater_id;
        this.theater_name = theater_name;

    }

    public JSONObject getData(){
        Schema sh = new Schema();
        JSONObject data = new Input().getData(sh.theater);

            return data;
    }
    public JSONObject data(){

        JSONObject data = new JSONObject();
        data.put("theater_id", this.theater_id);
        data.put("theater_name", this.theater_name);

        return data;
    }

    
}
