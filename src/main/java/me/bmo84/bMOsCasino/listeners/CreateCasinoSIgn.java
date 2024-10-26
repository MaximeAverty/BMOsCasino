package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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
            player.sendMessage(BMOsCasino.prefix + "The game §e" + gameName +" §f does not exist");
            return;
        }

        BMOsCasino.addSign(event.getBlock(), gameName);

        event.setLine(0, "");
        event.setLine(1, "§bDRAGON TOWER");

        player.sendMessage(BMOsCasino.prefix + "Casino créé avec succés !");

    }

}
