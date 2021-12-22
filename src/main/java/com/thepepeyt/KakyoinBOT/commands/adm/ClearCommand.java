package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class ClearCommand extends ListenerAdapter {
    private JDA jda;

    public ClearCommand(JDA jda) {
        this.jda = jda;
        jda.addEventListener(this);
        System.out.println("Clear registered");
    }


    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getName().equalsIgnoreCase("clear")) {
            if(!e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) return;


            var amount = e.getOptions().get(0).getAsString();
            if (amount.equals("all")) {
                amount = "100";
            }


            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Kakyoin | Clear")
                    .addField("Administrator", e.getMember().getUser().getName(), false)
                    .addField("Ilość", amount, false)
                    .setColor(App.getColor())
                    .setTimestamp(e.getTimeCreated())
                    .build();
            e.replyEmbeds(embed)
                    .queue(msg -> {
                        msg.deleteOriginal().queueAfter(10, TimeUnit.SECONDS);
                    });
            e.getTextChannel().deleteMessages(e.getChannel().getHistory().retrievePast(Integer.parseInt(amount)).complete()).queue();


        }
    }
}
