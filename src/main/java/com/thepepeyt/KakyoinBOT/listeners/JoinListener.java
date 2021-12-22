package com.thepepeyt.KakyoinBOT.listeners;




import com.thepepeyt.KakyoinBOT.App;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.util.List;

public class JoinListener extends ListenerAdapter {



    private JDA jda;

    public JoinListener(JDA jda) {
        this.jda = jda;
        System.out.println("Join registered");
    }

    @SneakyThrows
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {

        var embed = new EmbedBuilder()
                .setAuthor("Kakyoin | Witaj")
                .setDescription(e.getMember().getAsMention() + " witaj na naszym zjebanym serwerze:skull:")
                .setTimestamp(e.getMember().getTimeJoined())
                .setThumbnail(e.getMember().getAvatarUrl())
                .setColor(App.getColor())
                .build();



        jda.getTextChannelById("918992536331235390").sendMessageEmbeds(embed)
                .setActionRow(Button.primary("hello", "Witaj <3"))
                .queue();

        e.getGuild().addRoleToMember(e.getMember(), jda.getRoleById("920279428288897045")).queue();
        e.getGuild().addRoleToMember(e.getMember(), jda.getRoleById("920279764462354442")).queue();
        e.getGuild().addRoleToMember(e.getMember(), jda.getRoleById("921914986320769075")).queue();
        e.getGuild().addRoleToMember(e.getMember(), jda.getRoleById("922256029327192064")).queue();



    }

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        if (event.getComponentId().equals("hello")) {
            jda.getTextChannelById("918992536331235390").sendMessage(event.getMember().getAsMention() + " powitał nowego użytkownika").queue();
        }
    }



}
