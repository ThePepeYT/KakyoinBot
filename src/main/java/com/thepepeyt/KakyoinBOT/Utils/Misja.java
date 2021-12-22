package com.thepepeyt.KakyoinBOT.Utils;

import com.thepepeyt.KakyoinBOT.App;
import lombok.Getter;

public class Misja {
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private int difficulty;
    @Getter
    private int cash;
    @Getter
    private int time;

    public Misja(int difficulty, int cash, int time, String name, String description){
        this.difficulty = difficulty;
        this.cash = cash;
        this.time = time;
        this.name = name;
        this.description = description;

    }

    public static Misja[] generateMission(){
        Misja[] misje = new Misja[3];

        for (int i=0; i<3;i++) {
            var random = App.getLib().getRandomNum(1, 3);
            if(random == 3) misje[i] = new Misja(
                    random, App.getLib().getRandomNum(80, 150), App.getLib().getRandomNum(30, 40),"", "");

        }

    return misje;}
}
