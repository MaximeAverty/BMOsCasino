package me.bmo84.bMOsCasino.games;

import org.bukkit.Material;
import org.bukkit.entity.Player;


public abstract class CasinoGame {

    public String name;
    public String description;
    public Material gameIcon;

    public CasinoGame(String name, String description, Material gameIcon) {
        this.name = name;
        this.description = description;
        this.gameIcon = gameIcon;
    }

    public abstract void startGame(Player player);

    public abstract void openBetMenu(Player player);

}
