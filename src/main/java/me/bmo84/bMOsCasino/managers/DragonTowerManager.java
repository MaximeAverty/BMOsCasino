package me.bmo84.bMOsCasino.managers;

import me.bmo84.bMOsCasino.BMOsCasino;
import me.bmo84.bMOsCasino.games.CasinoGame;
import me.bmo84.bMOsCasino.games.DragonTowerGame;
import me.bmo84.bMOsCasino.menu.BetMenu;
import me.bmo84.bMOsCasino.menu.DragonTowerGUI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Map;

public class DragonTowerManager extends CasinoGame {

    private final Map<Player, DragonTowerGame> playersGames = new HashMap<>();
    private BMOsCasino plugin;

    public DragonTowerManager(String name, String description, Material gameIcon) {
        super(name, description, gameIcon);
        this.plugin = BMOsCasino.getPlugin();
    }

    @Override
    public void startGame(Player player) {
        player.closeInventory();

        double playerBet = BMOsCasino.getPlayersBet().get(player);
        double playerBalance = BMOsCasino.getEconomy().getBalance(player);

        if(!(getPlayersGames().get(player) == null)) {
            player.sendMessage(BMOsCasino.prefix + "§7You are already in a game");
            return;
        }

        if(playerBalance < playerBet) {
            player.sendMessage(BMOsCasino.prefix + "§7You dont have enough money");
            return;
        }
        BMOsCasino.getEconomy().withdrawPlayer(player, playerBet);

        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 20, 20);
        registerPlayerGame(player);
        new DragonTowerGUI(player);
    }


    @Override
    public void openBetMenu(Player player) {
        new BetMenu(player, gameIcon, name);
    }

    public void registerPlayerGame(Player player) {
        getPlayersGames().put(player, new DragonTowerGame(player, this));
    }

    public Map<Player, DragonTowerGame> getPlayersGames() {
        return this.playersGames;
    }

    public void deletePayerGame(Player player) {
        getPlayersGames().remove(player);
    }
}
