package com.thepepeyt.KakyoinBOT.listeners;




import com.thepepeyt.KakyoinBOT.App;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class JoinListener extends ListenerAdapter {



    private JDA jda;

    public JoinListener(JDA jda) {
        this.jda = jda;
        System.out.println("VerificationListener registered");
    }

    @SneakyThrows
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {

        App.getDB().insertInto("users",
                List.of("ID", "MONEY", "BANK", "REQ", "LEVEL", "EXP", "STANDS", "EKW"),
                List.of(e.getMember().getId(), 0, 0, 250, 1, 0, "brak", "pusty"));


        var embed = new EmbedBuilder()
                .setAuthor("Kakyoin | Witaj")
                .setDescription(e.getMember().getAsMention() + " witaj na naszym zjebanym serwerze:skull:")
                .setTimestamp(e.getMember().getTimeJoined())
                .setThumbnail(e.getMember().getAvatarUrl())
                .setColor(App.getColor())
                .build();



        jda.getTextChannelById("918968830326341643").sendMessageEmbeds(embed).queue();


    }



}
