package com.thepepeyt.KakyoinBOT.Utils;

import com.thepepeyt.KakyoinBOT.App;
import lombok.Getter;
import lombok.Setter;

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

    public Warns(int amount, String reasons, String admins, String dates){

        this.amount = amount;

        this.reasons = Arrays.stream(reasons.split(",")).collect(Collectors.toList());

        this.admins = Arrays.stream(admins.split(",")).collect(Collectors.toList());

        this.dates = Arrays.stream(dates.split(",")).collect(Collectors.toList());


    }

    public void addReason(String reason) {
        this.reasons.add("," + reason);
    }

    public void addAmount(){
        this.amount++;
    }

    public void addAdmins(String admin) {
        this.admins.add("," + admin);
    }

    public void addDates(String date) {
        this.dates.add("," + date);
    }

    @Override
    public String toString(){
        return App.getLib().ArraytoString(reasons, ",") +
                "," +
                App.getLib().ArraytoString(admins, ",") +
                "," +
                App.getLib().ArraytoString(dates, ",");
    }

}
