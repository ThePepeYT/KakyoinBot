package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class WarnCommand extends ListenerAdapter {
    private JDA jda;

    public WarnCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Warns registered");
    }


    @SneakyThrows
    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getName().equalsIgnoreCase("warn")) {
            if(!e.getMember().hasPermission(Permission.KICK_MEMBERS)) return;


            Member user = e.getGuild().retrieveMember(e.getOptions().get(0).getAsUser()).complete();
            String reason = e.getOptions().get(1).getAsString();
            String date = e.getTimeCreated().toString();


            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Kakyoin | Warn")
                    .addField("Administrator", e.getMember().getUser().getName(), true)
                    .addField("Użytkownik", user.getUser().getName(), true)
                    .addField("Powód", reason, true)
                    .setColor(App.getColor())
                    .setTimestamp(e.getTimeCreated())
                    .build();
            e.replyEmbeds(embed).queue();
            user.getUser().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessageEmbeds(embed))
                    .queue();

            var warns = App.getWarns(user.getId());
            warns.addAdmins(e.getMember().getNickname());
            warns.addReason(reason);
            warns.addDates(date);
            warns.addAmount();
            App.getWarns().replace(user.getId(), warns);
            App.getDB().updateColumn("WARNS", "AMOUNT", List.of("ID"), List.of(user.getId()), warns.getAmount());
            App.getDB().updateColumn("WARNS", "REASONS", List.of("ID"), List.of(user.getId()), App.getLib().ArraytoString(warns.getReasons(), ","));
            App.getDB().updateColumn("WARNS", "ADMINS", List.of("ID"), List.of(user.getId()), App.getLib().ArraytoString(warns.getAdmins(), ","));
            App.getDB().updateColumn("WARNS", "DATES", List.of("ID"), List.of(user.getId()), App.getLib().ArraytoString(warns.getDates(), ","));

            System.out.println(App.getWarns());

        }

    }
}
