package me.bmo84.bMOsCasino.games;

import me.bmo84.bMOsCasino.BMOsCasino;
import me.bmo84.bMOsCasino.menu.BetMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DragonTowerGame extends CasinoGame{

    public DragonTowerGame(String name, String description, Material gameIcon) {
        super(name, description, gameIcon);
    }

    @Override
    public void startGame(Player player, double bet) {
        player.closeInventory();
        player.sendMessage("Game started");
    }

    @Override
    public void openBetMenu(Player player) {
        new BetMenu(player, gameIcon, name);
    }
}
