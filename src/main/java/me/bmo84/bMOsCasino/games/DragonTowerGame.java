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
    public void onGameStart(Player player) {
        player.sendMessage("Hello Dragon game");
    }

    @Override
    public void openBetMenu(Player player) {
        new BetMenu(player, gameIcon, name);
    }
}
