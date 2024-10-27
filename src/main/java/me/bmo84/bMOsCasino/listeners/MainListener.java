package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class MainListener {

    public static void registerListener() {

        BMOsCasino plugin = BMOsCasino.getPlugin();

        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new CreateCasinoSIgn(), plugin);
        pm.registerEvents(new BreakSign(), plugin);
        pm.registerEvents(new ClickSign(), plugin);
    }

}
