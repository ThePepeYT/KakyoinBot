package com.thepepeyt.KakyoinBOT.Utils;

import com.thepepeyt.KakyoinBOT.App;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class EconomyEQ {

    @Setter
    @Getter
    private int arrow = 0;
    @Setter
    @Getter
    private int ropa = 0;
    @Setter
    @Getter
    private int najemnicy  = 0;
    @Setter
    @Getter
    private int companies = 0;
    @Setter
    @Getter
    private String name = "";

    @Setter
    @Getter
    private int money,bank;




    public EconomyEQ(String economy, String name, int money, Object bank){

        if(bank == null){
            this.bank = 0;
        }
        else{
            this.bank = (Integer) bank;
        }

        var xD = App.getLib().StringtoArray(economy, ",");

                xD.forEach(x -> {

                    var values = App.getLib().StringtoArray(x, ":");
                    try {
                        Field field = getClass().getDeclaredField(values.get(0));
                        field.setInt(this, Integer.parseInt(values.get(1)));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                });




        this.name = name;
        this.money = money;
    }

    @SneakyThrows
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            if(!field.getName().equals("name")) {
                str.append(field.getName() + ":" + field.getInt(this) + ",");
            }
        }


        return str.substring(0, str.length() - 1);



    }

    public void addMoney(int money){
        this.money = this.money + money;
    }
}
