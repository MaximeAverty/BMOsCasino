package me.bmo84.bMOsCasino;

import me.bmo84.bMOsCasino.games.CasinoGame;
import me.bmo84.bMOsCasino.games.DragonTowerGame;
import me.bmo84.bMOsCasino.listeners.CreateCasinoSIgn;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class BMOsCasino extends JavaPlugin {

    private static Economy econ = null;
    private static BMOsCasino plugin;

    public static String prefix = "§f[§9Bmo's Casino§f] ";

    private static final Map<String, String> signsMap = new HashMap<String, String>();
    private static final Map<String, CasinoGame> casinoGames = new HashMap<String, CasinoGame>();

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

        Bukkit.getServer().getPluginManager().registerEvents(new CreateCasinoSIgn(), this);

        casinoGames.put("DRAGON TOWER", new DragonTowerGame("Dragon Tower", "empty"));

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

    public static void addSign(Block sign, String gameName) {

        String signKey = sign.getWorld().getName() + "/" + sign.getX() + "/" + sign.getY() + "/" + sign.getZ();
        plugin.getConfig().set(signKey, gameName);
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
