package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class BreakSign implements Listener {

    @EventHandler
    public void onBreakSign(BlockBreakEvent event) {

        if(!(event.getBlock().getType().toString().endsWith("_SIGN"))) {
            return;
        }

        Location signLocation = event.getBlock().getLocation();

        String signLocToString = Objects.requireNonNull(signLocation.getWorld()).getName() + "/"
                + signLocation.getBlockX() + "/"
                + signLocation.getBlockY() + "/"
                + signLocation.getBlockZ();

        ConfigurationSection configSection = BMOsCasino.getPlugin().getConfig().getConfigurationSection("casinoSigns");

        if (Objects.requireNonNull(configSection).contains(signLocToString)) {
            Objects.requireNonNull(configSection).set(signLocToString, null);
            BMOsCasino.getPlugin().saveConfig();
        }
    }

}
