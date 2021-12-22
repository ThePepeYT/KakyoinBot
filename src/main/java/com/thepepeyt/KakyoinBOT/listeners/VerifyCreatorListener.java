package com.thepepeyt.KakyoinBOT.listeners;

import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class VerifyCreatorListener extends ListenerAdapter {


    public VerifyCreatorListener() {
        System.out.println("VerificationListener registered");
    }

    @Override
    public void onReady(ReadyEvent e){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Witajcie na naszym serwerze", "https://images-ext-1.discordapp.net/external/QbvrgxikqqXyNyMRX8O3pZGgSrXDfKl2j33vjBhPCAA/%3Fv%3D1595979443666/https/cdn.glitch.com/13bc906c-313f-4770-ad2f-87b316d17468%252Flock-open-icon_34398.png");
        embed.setDescription("Ten serwer jest chroniony prostym zabezpieczeniem\n" +
                "Aby uczestnić w tej pięknej społeczności musisz się zweryfikować");
        embed.addField("**Problem?**", "Podążaj za tą instrukcją\n•Zezwól na DM z serwera\n•Naciśnij na przycisk \n•Wpisz hasło", false);
        embed.setFooter("Podaj tutaj hasło plox");
        embed.setImage("https://cdn.discordapp.com/attachments/741966016233603092/743094073921110096/tutorial.gif");
        embed.setColor(Color.decode("#0099E1"));



        e.getJDA().getTextChannelById("923004305307492442").sendMessageEmbeds(embed.build())
                .setActionRow(Button.primary("verify", "Hasło"))
                .queue();



    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("verify")) {
            event.getUser().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessage("Hasło na serwerze to: **speedwagon**"))
                    .queue();
        }
    }

    @Override
    public void onMessageReceived(@NonNull MessageReceivedEvent e) {
        if(!(e.getChannel() instanceof TextChannel)) return;
        if(!e.getTextChannel().getId().equals("923004305307492442")) return;
        e.getMessage().delete().queueAfter(2, TimeUnit.SECONDS);
        if(!e.getMessage().getContentRaw().toLowerCase(Locale.ROOT).equals("speedwagon")) return;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Witaj " + e.getMember().getNickname());
        embedBuilder.setDescription("Zostałeś pomyślnie zweryfikowany na **niebie crusaderów**\nŻyczymy miłej zabawy");
        embedBuilder.setTimestamp(e.getMessage().getTimeCreated());
        embedBuilder.setColor(Color.decode("#57F287"));
        e.getMember().getUser().openPrivateChannel()
                .flatMap(channel -> channel.sendMessageEmbeds(embedBuilder.build()))
                .queue();

        e.getGuild().addRoleToMember(e.getMember().getId(), e.getJDA().getRoleById("918991212243984404")).queue();

    }




}
