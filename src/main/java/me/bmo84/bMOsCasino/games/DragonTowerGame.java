package me.bmo84.bMOsCasino.games;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.entity.Player;

public class DragonTowerGame extends CasinoGame{

    public DragonTowerGame(String name, String description) {
        super(name, description);
    }

    @Override
    public void onGameStart(Player player) {
        player.sendMessage("Hello Dragon game");
    }
}
