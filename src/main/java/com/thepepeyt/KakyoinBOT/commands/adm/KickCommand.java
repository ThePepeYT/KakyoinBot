package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class KickCommand extends ListenerAdapter {
    private JDA jda;

    public KickCommand(JDA jda) {
        this.jda = jda;
        jda.addEventListener(this);
        System.out.println("Ping registered");
    }


    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getName().equalsIgnoreCase("kick")) {
            if(!e.getMember().hasPermission(Permission.KICK_MEMBERS)) return;


            Member user = e.getGuild().retrieveMember(e.getOptions().get(0).getAsUser()).complete();
            String reason = e.getOptions().get(1).getAsString();


            MessageEmbed embed = new EmbedBuilder()
                    .setAuthor("Kakyoin | Kick")
                    .addField("Administrator", e.getMember().getUser().getName(), true)
                    .addField("Użytkownik", user.getUser().getName(), true)
                    .addField("Powód", reason, true)
                    .setColor(App.getColor())
                    .setTimestamp(e.getTimeCreated())
                    .build();
            e.replyEmbeds(embed).queue();
            e.getGuild().kick(user).queue();
            user.getUser().openPrivateChannel()
                    .flatMap(channel -> channel.sendMessageEmbeds(embed))
                    .queue();

        }
    }
}
