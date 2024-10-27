package me.bmo84.bMOsCasino;

import me.bmo84.bMOsCasino.games.CasinoGame;
import me.bmo84.bMOsCasino.listeners.MainListener;
import me.bmo84.bMOsCasino.managers.DragonTowerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.block.Block;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class BMOsCasino extends JavaPlugin {

    private static Economy econ = null;
    private static BMOsCasino plugin;

    public static String prefix = "§f[§9§lBmo's Casino§f] ";

    private static final Map<String, String> signsMap = new HashMap<String, String>();
    private static final Map<Player, Double> playersBet = new HashMap<Player, Double>();
    private static final Map<String, CasinoGame> casinoGames = new HashMap<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getLogger().info("Server demarré");

        this.saveDefaultConfig();
        loadSignsFromConfig();

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        plugin = this;


        // TODO Save games before listeners
        casinoGames.put("DRAGON TOWER", new DragonTowerManager("DRAGON TOWER", "empty", Material.DRAGON_HEAD));

        MainListener.registerListener();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        if(econ != null) {
            return econ;
        }
        return null;
    }

    public static BMOsCasino getPlugin() {
        return plugin;
    }

    public static Map<String, String> getSignsMap() {
        return signsMap;
    }

    public static Map<String, CasinoGame> getCasinoGames() {
        return casinoGames;
    }

    public static Map<Player, Double> getPlayersBet() {
        return playersBet;
    }

    public static void addPlayerBet(Player player, Double bet) {
        getPlayersBet().put(player, bet);
    }

    public static void addSign(Block sign, String gameName) {

        String signKey = sign.getWorld().getName() + "/" + sign.getX() + "/" + sign.getY() + "/" + sign.getZ();
        Objects.requireNonNull(plugin.getConfig().getConfigurationSection("casinoSigns")).set(signKey, gameName);
        plugin.saveConfig();

    }

    private void loadSignsFromConfig() {

        if(!getConfig().contains("casinoSigns")) {
            getConfig().createSection("casinoSigns");
            saveConfig();
            return;
        }

        for(String keyMap : getConfig().getConfigurationSection("casinoSigns").getKeys(false)) {

            String gameName = getConfig().getString("casinoSigns." + keyMap);
            if(gameName != null) {
                getSignsMap().put(keyMap, gameName);
                Bukkit.getLogger().info("Signs loaded successfully!");
            }
        }
    }
}
