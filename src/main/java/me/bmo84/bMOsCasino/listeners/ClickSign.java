package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import me.bmo84.bMOsCasino.games.CasinoGame;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class ClickSign implements Listener {

    @EventHandler
    public void onClickCasinoSign(PlayerInteractEvent event) {

        if(event.getClickedBlock() == null) {
            return;
        }

        if(!(Objects.requireNonNull(event.getClickedBlock()).getType().toString().endsWith("_SIGN"))) {
            return;
        }

        if(!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        BMOsCasino plugin = BMOsCasino.getPlugin();

        Location signLoc = event.getClickedBlock().getLocation();
        String keyMap = Objects.requireNonNull(signLoc.getWorld()).getName() + "/"
                + signLoc.getBlockX() + "/"
                + signLoc.getBlockY() + "/"
                + signLoc.getBlockZ();

        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("casinoSigns");

        if(configSection.contains(keyMap)) {

            event.setCancelled(true);
            String gameName = configSection.getString(keyMap);

            CasinoGame game = BMOsCasino.getCasinoGames().get(gameName);

            game.openBetMenu(event.getPlayer());

        }

    }

}
