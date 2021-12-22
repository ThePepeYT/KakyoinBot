package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Emoji;
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

        var tak = e.getGuild().retrieveEmoteById("923161383523201024").complete();

        var nie = e.getGuild().retrieveEmoteById("923161969475850290").complete();

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("Kakyoin | Ankiety");
        embedBuilder.setDescription(pytanko);
        embedBuilder.setColor(App.getColor());
        embedBuilder.setTimestamp(e.getTimeCreated());
        embedBuilder.addField("Odpowiedż:", tak.getAsMention() + " -> tak\n" + nie.getAsMention() + " -> nie", false);

        e.getChannel().sendMessageEmbeds(embedBuilder.build()).queue(msg -> {
            msg.addReaction(tak).queue();
            msg.addReaction(nie).queue();
        });

    }
}
