package com.thepepeyt.KakyoinBOT.commands.economy;

import com.thepepeyt.KakyoinBOT.App;
import com.thepepeyt.KakyoinBOT.Utils.EconomyEQ;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BiznesinfoCommand extends ListenerAdapter {
    private JDA jda;

    public BiznesinfoCommand(JDA jda) {
        this.jda = jda;
        System.out.println("Biznesinfo registered");
    }





    @SneakyThrows
    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (!e.getName().equals("biznesinfo")) return;
        User user = e.getUser();
        if(!e.getOptions().isEmpty()) user = e.getOptions().get(0).getAsUser();
        EconomyEQ EQ = App.getBiznes(user.getId());
        var embed = new EmbedBuilder();
        embed.setAuthor("Kakyoin | Biznes");
        embed.addField("Nazwa biznesu", EQ.getName(), false);
        embed.addField("Pieniądze:", "Bank: " + EQ.getBank() + "\nKasa: " + EQ.getMoney(), false);
        embed.addField("Akcje:",
                "Pola ropy: " + EQ.getRopa() + "\nTajemnicze Strzały: " + EQ.getArrow() + "\nNajemnicy: " + EQ.getNajemnicy() + "\nSpółki:" +  EQ.getCompanies(), false);
        embed.setColor(App.getColor());
        embed.setTimestamp(e.getTimeCreated());
        e.replyEmbeds(embed.build()).queue();

        System.out.println(App.getMap());


    }
}
