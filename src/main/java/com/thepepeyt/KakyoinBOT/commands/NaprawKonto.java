package com.thepepeyt.KakyoinBOT.commands;

import com.thepepeyt.KakyoinBOT.App;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;
import java.util.List;

public class NaprawKonto extends ListenerAdapter {

    private JDA jda;

    public NaprawKonto(JDA jda) {
        this.jda = jda;
        System.out.println("NaprawKonto registered");
    }





    @SneakyThrows
    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (!e.getName().equals("przebudzenie")) return;
        App.getDB().ifExists("users", List.of("ID"), List.of(e.getMember().getId()))
                        .thenAccept(bool -> {
                            if(bool.booleanValue()){
                                var embed = new EmbedBuilder()
                                        .setAuthor("Kakyoin | Przebudzenie")
                                        .setDescription("Już raz przebudziłeś standa bruh")
                                        .setTimestamp(e.getMember().getTimeJoined())
                                        .setThumbnail(e.getMember().getAvatarUrl())
                                        .setColor(App.getColor())
                                        .build();

                                e.replyEmbeds(embed).queue();
                            }
                            else{

                                try {
                                    App.getDB().insertInto("users",
                                            List.of("ID", "MONEY", "BANK", "REQ", "LEVEL", "EXP", "STANDS", "EKW"),
                                            List.of(e.getMember().getId(), 0, 0, 250, 1, 0, "brak", "pusty"));
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }


                                var embed = new EmbedBuilder()
                                        .setAuthor("Kakyoin | Przebudzenie")
                                        .setDescription("Brawo udało ci się przebudzić standa (wkrótce)")
                                        .setTimestamp(e.getMember().getTimeJoined())
                                        .setThumbnail(e.getMember().getAvatarUrl())
                                        .setColor(App.getColor())
                                        .build();

                                e.replyEmbeds(embed).queue();

                            }

                        });


    }
}
