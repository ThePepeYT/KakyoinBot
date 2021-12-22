package com.thepepeyt.KakyoinBOT;




import com.thepepeyt.KakyoinBOT.Utils.EconomyEQ;
import com.thepepeyt.KakyoinBOT.Utils.Warns;
import com.thepepeyt.KakyoinBOT.commands.*;
import com.thepepeyt.KakyoinBOT.commands.adm.*;
import com.thepepeyt.KakyoinBOT.commands.economy.BiznesinfoCommand;
import com.thepepeyt.KakyoinBOT.commands.economy.CreatebiznesCommand;

import com.thepepeyt.KakyoinBOT.listeners.AntiSendDiscordLinkListener;



import com.thepepeyt.KakyoinBOT.listeners.JoinListener;
import com.thepepeyt.FastLibJava.FastLibJava;
import com.thepepeyt.KakyoinBOT.listeners.VerifyCreatorListener;
import com.thepepeyt.databasehelper.DatabaseHelper;
import com.thepepeyt.databasehelper.database.type.PostgreSQL;
import com.thepepeyt.databasehelper.database.type.SQLite3;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class App {
    @Getter
    private static Path path;
    @Getter
    private final static FastLibJava lib = new FastLibJava();
    @Getter
    private static HashMap<String, EconomyEQ> map = new HashMap<>();
    @Getter
    private static HashMap<String, Warns> warns = new HashMap<>();
    @Getter
    private static Color color = Color.decode("#A652BB");


    @Getter
    private final static PostgreSQL DB = (PostgreSQL) DatabaseHelper.postgreSQLBuilder()
            .host("ec2-54-78-211-131.eu-west-1.compute.amazonaws.com")
            .database("d2c6hvovj3hvbr")
            .user("rgaulelvlixiys")
            .port(5432)
            .password("e1348f2fcd79a1e60cd57f14cdf8f2b69910e3d93fa4cadaf7bf5c21dd50fb79")
            .build();




    public static void main(String[] args) throws LoginException, InterruptedException, SQLException, ClassNotFoundException {




        final var jda = JDABuilder.createLight(System.getenv("TOKEN"))
                .addEventListeners(new VerifyCreatorListener())
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS)
                .build();







        jda.addEventListener(new InfoCommand(jda));
        jda.addEventListener(new CiekawostkaCommand(jda));
        jda.addEventListener(new NaprawKonto(jda));
        jda.addEventListener(new BiznesinfoCommand(jda));
        jda.addEventListener(new CreatebiznesCommand(jda));
        jda.addEventListener(new WarnCommand(jda));
        jda.addEventListener(new WarnlistCommand(jda));
        jda.addEventListener(new EmbedCommand(jda));
        new KickCommand(jda);
        new ClearCommand(jda);
        new MuteCommand(jda);

        jda.addEventListener(new AntiSendDiscordLinkListener(jda));
        jda.addEventListener(new JoinListener(jda));

        jda.awaitReady();


        initialize(jda);


        DB.connect();

        DB.createTable(
                "level",
                List.of("ID TEXT","REQ INT", "LEVEL INT", "EXP INT"));


        DB.createTable("byznes",
                List.of("ID TEXT", "MONEY INT", "SEJF INT", "NAZWA TEXT", "ITEMY TEXT")
        );


        DB.createTable("WARNS",
                List.of("ID TEXT", "AMOUNT INT", "JSON TEXT")
        );

        EconomyEQ EQ = new EconomyEQ("arrow:0,ropa:0,najemnicy:0,companies:0", "brak biznesu", 0,0);

        map.put("0", EQ);








        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                map.forEach((x,y) -> {
                    try {
                        DB.updateColumn("byznes", "MONEY", List.of("ID"), List.of(x), y.getMoney());
                        DB.updateColumn("byznes", "SEJF", List.of("ID"), List.of(x), y.getBank());
                        DB.updateColumn("byznes", "NAZWA", List.of("ID"), List.of(x), y.getName());
                        DB.updateColumn("byznes", "ITEMY", List.of("ID"), List.of(x), y.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        });



    }



    public static void initialize(JDA jda){
        var timings = lib.createTimings("initialize");

        List<CommandData> cmds = new ArrayList<CommandData>();


        timings.start();

        cmds.add(new CommandData("ping", "Sprawdz ping bota"));

        System.out.println("[SLASH] ping created");

        cmds.add(new CommandData("przebudzenie", "Przebudzenie swojego konta oraz standa :)"));

        System.out.println("[SLASH] przebudzenie created");

        cmds.add(new CommandData("ciekawostka", "Randomowa ciekawostka na temat świata jojo"));

        System.out.println("[SLASH] ciekawostka created");

        cmds.add(new CommandData("dodajciekawostke", "Dodaj randomową ciekawostke")
                .addOption(OptionType.STRING, "kontent", "Napisz tu ciekawostkę"));

        System.out.println("[SLASH] dodaj created");

        cmds.add(new CommandData("zalozbiznes", "Załóz swój własny biznes")
                .addOption(OptionType.STRING, "nazwa", "Nazwa swojego biznesu"));

        System.out.println("[SLASH] dodaj created");





        cmds.add(new CommandData("mute", "zmutuj niegrzecznego użytkownika")
                .addOption(OptionType.USER, "użytkownik", "Kogo chcesz zmutować")
                .addOption(OptionType.STRING, "powód", "Powód dlaczego chcesz kogoś zmutować"));

        System.out.println("[SLASH] mute created");

        cmds.add(new CommandData("kick", "wyrzuć użytkownika")
                .addOption(OptionType.USER, "użytkownik", "Kogo chcesz wyrzucić")
                .addOption(OptionType.STRING, "powód", "Dlaczego chcesz kogoś wyrzucić"));

        System.out.println("[SLASH] kick created");

        cmds.add(new CommandData("warn", "Ostrzeż użytkownika")
                .addOption(OptionType.USER, "użytkownik", "Kogo chcesz ostrzec")
                .addOption(OptionType.STRING, "powód", "Dlaczego chcesz ostrzec"));

        System.out.println("[SLASH] warn created");


        cmds.add(new CommandData("embed", "stwórz embeda i wyślij go na kanał")
                .addOption(OptionType.CHANNEL, "channel", "kanał na który ma zostać wysłany", true)
                .addOption(OptionType.STRING, "title", "Tytuł embeda", false)
                .addOption(OptionType.STRING, "author", "autor embeda", false)
                .addOption(OptionType.STRING, "description", "Opis embeda", false)
                .addOption(OptionType.STRING, "footer", "stopka embeda", false)
                .addOption(OptionType.STRING, "color", "kolor embeda", false));

        System.out.println("[SLASH] embed created");


        System.out.println("[SLASH] warn created");

        cmds.add(new CommandData("clear", "Wyczyść kanał z wiadomości")
                .addOption(OptionType.STRING, "ilość", "Podaj ile wiadomości chcesz skasować"));



        cmds.add(new CommandData("biznesinfo", "sprawdż biznes innego użytkownika")
                .addOption(OptionType.USER, "użytkownik", "Kogo biznes chcesz sprawdzić", false));

        System.out.println("[SLASH] biznesinfo created");



        cmds.add(new CommandData("warnlist", "sprawdż ostrzeżenia użytkownika")
                .addOption(OptionType.USER, "użytkownik", "Kogo ostrzeżenia chcesz sprawdzić", false));

        System.out.println("[SLASH] warnlist created");




        jda.getGuildById("882032492457132032").updateCommands().addCommands(cmds).queue();
        timings.stop();
        System.out.println(timings.toString());

    }



    public static EconomyEQ getBiznes(String id) throws SQLException, ExecutionException, InterruptedException {
        if(map.containsKey(id)) return map.get(id);
        final Boolean check = DB.ifExists("byznes", List.of("ID"), List.of(id)).get();
        if(!check) return map.get("0");
        var list = DB.getColumns("byznes", List.of("ITEMY", "NAZWA","MONEY", "SEJF"), List.of("ID"), List.of(id)).get();
        EconomyEQ EQ = new EconomyEQ((String) list.get().get(0), (String) list.get().get(1), (Integer) list.get().get(2), list.get().get(3));


        map.put(id, EQ);
        return EQ;
    }

    public static void updateEconomy(String id, EconomyEQ EQ){
        map.replace(id, EQ);
    }

    public static Warns getWarns(String id) throws SQLException, ExecutionException, InterruptedException, ParseException {
        if (warns.containsKey(id)) return warns.get(id);
        final Boolean check = DB.ifExists("WARNS", List.of("ID"), List.of(id)).get();
        if (!check) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("reasons", new ArrayList<String>());
            jsonObject.put("admins", new ArrayList<String>());
            jsonObject.put("dates", new ArrayList<String>());
            DB.CustomSQLVoid(
                    "INSERT INTO WARNS (ID,AMOUNT,JSON) VALUES (?,?,?)",
                    List.of(id, 0, jsonObject.toJSONString()));

            var warn = new Warns(0, jsonObject);
            warns.put(id, warn);
            return warn;
        }

        var list = App.getDB().getColumns("WARNS", List.of("AMOUNT", "JSON"), List.of("ID"), List.of(id)).get().get();

        Warns warn = new Warns((Integer) list.get(0), (JSONObject) new JSONParser().parse((String) list.get(1)));


        warns.put(id, warn);
        return warn;

    }



}
