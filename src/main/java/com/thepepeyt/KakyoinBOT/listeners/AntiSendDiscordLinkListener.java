package com.thepepeyt.KakyoinBOT.listeners;

import com.thepepeyt.KakyoinBOT.App;
import lombok.NonNull;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.util.concurrent.TimeUnit;

public class AntiSendDiscordLinkListener extends ListenerAdapter {

    private JDA jda;

    public AntiSendDiscordLinkListener(JDA jda) {
        this.jda = jda;
        System.out.println("Antylink registered");
    }

    @SneakyThrows
    @Override
    public void onMessageReceived(@NonNull MessageReceivedEvent e) {
        if (e.getMember() == null) return;
        if (!e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (e.getMessage().getContentRaw().contains("discord.gg/")) {
                var embed = new EmbedBuilder()
                        .setAuthor("Kakyoin | AntyLink")
                        .setDescription("Ty chuju bobrze przestań mi serwer prześladować")
                        .setColor(App.getColor())
                        .setTimestamp(e.getMessage().getTimeCreated())
                        .build();
                e.getMessage().delete().queue();
                e.getTextChannel().sendMessageEmbeds(embed).queue(msg -> {
                    msg.delete().queueAfter(10, TimeUnit.SECONDS);

                });

            }
        }
    }
}
