package com.thepepeyt.KakyoinBOT.commands;

import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;


public class CiekawostkaCommand extends ListenerAdapter {

    private JDA jda;

    public CiekawostkaCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Ciekawostka registered");
    }
    List<Object> ciekawostki = new ArrayList<>();



    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getName().equals("dodajciekawostke")) {
            if(!e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) return;
            String kontent = e.getOptions().get(0).getAsString();
            ciekawostki.add(kontent);
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Kakyoin | Ciekawostki")
                    .setDescription("Pomyślnie dodano twoją ciekawostke")
                    .setColor(App.getColor())
                    .setTimestamp(e.getTimeCreated())
                    .build();
            e.replyEmbeds(embed).queue();




        }
        if(e.getName().equals("ciekawostka")) {
            var ciekawe = App.getLib().getRandomfromList(ciekawostki).toString();
            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Kakyoin | Ciekawostki")
                    .setDescription(ciekawe)
                    .setColor(App.getColor())
                    .setTimestamp(e.getTimeCreated())
                    .setFooter("Jestem " + (ciekawostki.indexOf(ciekawe) + 1) + " na liście ciekawostek")
                    .build();

            e.replyEmbeds(embed).queue();

        }
    }
}
