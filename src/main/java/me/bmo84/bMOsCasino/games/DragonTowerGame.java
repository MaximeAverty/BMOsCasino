package me.bmo84.bMOsCasino.games;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.entity.Player;

public class DragonTowerGame {

    private int currentRow = 1;

    private Player player;
    private double playerBet;

    public DragonTowerGame(Player player) {
        this.player = player;
        this.playerBet = BMOsCasino.getPlayersBet().get(player);
    }

    // Logique et autre fonction pour les calcule etc...

}
