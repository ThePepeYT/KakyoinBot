package com.thepepeyt.KakyoinBOT.Utils;

import com.google.gson.JsonObject;
import com.thepepeyt.KakyoinBOT.App;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Warns {

    @Setter
    @Getter
    private int amount;

    @Getter
    private List<String> reasons;

    @Getter
    private List<String> admins;


    @Getter
    private List<String> dates;

    public Warns(int amount, JSONObject json){

        this.amount = amount;

        this.reasons = (List<String>) json.get("reasons");

        this.admins = (List<String>) json.get("admins");

        this.dates = (List<String>) json.get("dates");

    }


    public JSONObject asJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reasons", reasons);
        jsonObject.put("admins", admins);
        jsonObject.put("dates", dates);
        return jsonObject;
    }

    public void addAdmin(String admin){
        this.admins.add(admin);
    }
    public void addDate(String date){
        this.dates.add(date);
    }
    public void addReason(String reason){
        this.reasons.add(reason);
    }

    public void addAmount(){
        this.amount++;
    }

}
