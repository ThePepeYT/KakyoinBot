package com.thepepeyt.KakyoinBOT.commands.economy;

import com.thepepeyt.KakyoinBOT.App;
import com.thepepeyt.KakyoinBOT.Economy.EconomyEQ;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CreatebiznesCommand extends ListenerAdapter {
    private JDA jda;

    public CreatebiznesCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Ping registered");
    }




    @SneakyThrows
    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (!e.getName().equals("zalozbiznes")) return;
        App.getDB().ifExists("byznes", java.util.List.of("ID"), java.util.List.of(e.getMember().getId()))
                .thenAccept(bool -> {
                    if(bool.booleanValue()){
                        var embed = new EmbedBuilder()
                                .setAuthor("Kakyoin | Biznes")
                                .setDescription("Już masz jeden biznes bruh")
                                .setTimestamp(e.getMember().getTimeJoined())
                                .setThumbnail(e.getMember().getAvatarUrl())
                                .setColor(Color.RED)
                                .build();

                        e.replyEmbeds(embed).queue();
                    }
                    else{
                        String biznesname = e.getOptions().get(0).getAsString();

                        try {


                            App.getDB().CustomSQLVoid(
                                    "INSERT INTO byznes (ID,MONEY,SEJF,NAZWA,ITEMY) VALUES (?,?,?,?,?)",
                                    List.of(e.getMember().getId(), 0, 0, biznesname, "arrow:0,ropa:0,najemnicy:0,companies:0"));


                            EconomyEQ EQ = new EconomyEQ("arrow:0,ropa:0,najemnicy:0,companies:0", biznesname, 0,0);
                            App.getMap().put(e.getMember().getId(), EQ);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


                        var embed = new EmbedBuilder()
                                .setAuthor("Kakyoin | Biznes")
                                .setDescription("Utworzyłeś swój biznes o nazwie " + biznesname)
                                .setTimestamp(e.getMember().getTimeJoined())
                                .setThumbnail(e.getMember().getAvatarUrl())
                                .setColor(App.getColor())
                                .build();

                        e.replyEmbeds(embed).queue();

                    }

                });


    }
}
