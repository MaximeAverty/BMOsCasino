package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.util.Objects;

public class CreateCasinoSIgn implements Listener {

    @EventHandler
    public void createCasino(SignChangeEvent event) {

        Player player = event.getPlayer();

        if(!(player.hasPermission("bmoscasino.create.casino"))) {
            return;
        }


        if(!(Objects.equals(event.getLine(0), "[CASINO]"))) {
            return;
        }

        String gameName = event.getLine(1);

        if(!(BMOsCasino.getCasinoGames().containsKey(gameName))) {
            player.sendMessage(BMOsCasino.prefix + "The game §e" + gameName +" §fdoes not exist");
            return;
        }

        BMOsCasino.addSign(event.getBlock(), gameName);

        event.setLine(0, "");
        event.setLine(1, "§b" + gameName);

        player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 40, 33);

        player.sendMessage(BMOsCasino.prefix + "§aCasino created successfully!");

    }

}
