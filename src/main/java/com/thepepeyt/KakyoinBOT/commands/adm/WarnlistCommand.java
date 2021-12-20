package com.thepepeyt.KakyoinBOT.commands.adm;

import com.thepepeyt.KakyoinBOT.App;
import com.thepepeyt.KakyoinBOT.Utils.Warns;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WarnlistCommand extends ListenerAdapter {

    private JDA jda;

    public WarnlistCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Warnlist registered");
    }




    @SneakyThrows
    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if(!e.getName().equals("warnlist")) return;
        Warns warns = App.getWarns(e.getOptions().get(0).getAsUser().getId());
        System.out.println(warns);
        var embed = new EmbedBuilder();
        embed.setAuthor("Kakyoin | Warnlist");
        embed.setDescription("Liczba warnów: " + warns.getAmount());
        warns.getReasons().forEach(x -> {
            var index = warns.getReasons().indexOf(x);
            embed.addField("Warn #" + (index + 1), "Powód: " + x +
                    "\nAdmin: " + warns.getAdmins().get(index) + "\n Data: " + warns.getDates().get(index), false);


            embed.setColor(App.getColor());
            embed.setTimestamp(e.getTimeCreated());




        });

        e.reply("zbieram dane").queue(x -> x.editOriginalEmbeds(embed.build()).queue());


    }
}
