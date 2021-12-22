package com.thepepeyt.KakyoinBOT.commands;


import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InfoCommand extends ListenerAdapter {

    private JDA jda;

    public InfoCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Ping registered");
    }

    public String status(Long time){
        if(time > 500){
            return "KING CRIMSON";
        }
        if(time > 300){
            return "Frog";
        }
        if(time > 100){
            return "MADE IN HEAVEN";
        }
        else{
            return "ZA WAURDO";
        }

    }



    @Override
    public void onSlashCommand(SlashCommandEvent e)
    {
        if (!e.getName().equals("ping")) return; // make sure we handle the right command
        long time = System.currentTimeMillis();

        e.reply("Pong!")
                .queue(msg -> {
                    var xD = (System.currentTimeMillis() - time);
        MessageEmbed embed = new EmbedBuilder()
                .setAuthor("Kakyoin | Ping")
                .setDescription("Mój ping wynosi: " + xD + "ms\nPrędkość: " + status(xD))
                .setColor(App.getColor())
                .setTimestamp(e.getTimeCreated())
                .build();
        msg.editOriginalEmbeds(embed).queue();

    }); // Queue both reply and edit
    }
}
