package me.bmo84.bMOsCasino.managers;

import me.bmo84.bMOsCasino.games.CasinoGame;
import me.bmo84.bMOsCasino.games.DragonTowerGame;
import me.bmo84.bMOsCasino.menu.BetMenu;
import me.bmo84.bMOsCasino.menu.DragonTowerGUI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DragonTowerManager extends CasinoGame {

    private final Map<Player, DragonTowerGame> playersGames = new HashMap<>();

    public DragonTowerManager(String name, String description, Material gameIcon) {
        super(name, description, gameIcon);
    }

    @Override
    public void startGame(Player player) {
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 20, 20);
        new DragonTowerGUI(player);
    }

    @Override
    public void openBetMenu(Player player) {
        new BetMenu(player, gameIcon, name);
    }

    public void registerPlayerGame(Player player) {
        getPlayersGames().put(player, new DragonTowerGame(player));
    }

    public Map<Player, DragonTowerGame> getPlayersGames() {
        return this.playersGames;
    }
}
