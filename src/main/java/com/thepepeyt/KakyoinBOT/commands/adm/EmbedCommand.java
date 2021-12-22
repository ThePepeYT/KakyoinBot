package com.thepepeyt.KakyoinBOT.commands.adm;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class EmbedCommand extends ListenerAdapter {

    private JDA jda;

    public EmbedCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Embed registered");
    }




    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (!e.getName().equals("embed")) return;
        if (!e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) return;
        var channel = e.getOption("channel").getAsMessageChannel();
        var title = e.getOption("title");
        var author = e.getOption("author");
        var description = e.getOption("description");
        var footer = e.getOption("footer");
        var color = e.getOption("color");

        var embed = new EmbedBuilder();

        if(title != null) embed.setTitle(title.getAsString());
        if(author != null) embed.setAuthor(author.getAsString());
        if(description != null) embed.setDescription(description.getAsString());
        if(footer != null) embed.setFooter(footer.getAsString());
        if(color != null) embed.setColor(Color.decode(color.getAsString()));
        embed.setTimestamp(e.getTimeCreated());


        channel.sendMessageEmbeds(embed.build()).queue();
    }

}
