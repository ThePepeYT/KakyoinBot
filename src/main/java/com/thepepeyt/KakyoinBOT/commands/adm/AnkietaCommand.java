package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AnkietaCommand extends ListenerAdapter {

    private JDA jda;

    public AnkietaCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Ping registered");
    }


    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (!e.getName().equals("ankieta")) return;
        if (!e.getMember().hasPermission(Permission.MESSAGE_MANAGE)) return;
        String pytanko = e.getOptions().get(0).getAsString();
        var tak = e.getGuild().getEmoteById("923161383523201024");
        var nie = e.getGuild().getEmoteById("923161969475850290");

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Kakyoin | Clear");
        embedBuilder.setDescription(pytanko);
        embedBuilder.setColor(App.getColor());
        embedBuilder.setTimestamp(e.getTimeCreated());
        embedBuilder.addField("Odpowiedż:", tak.getAsMention() + " -> takm\n" + nie.getAsMention() + " -> nie", false);

        e.getChannel().sendMessageEmbeds(embedBuilder.build()).queue(msg -> {
            msg.addReaction(tak).queue();
            msg.addReaction(nie).queue();
        });

    }
}
