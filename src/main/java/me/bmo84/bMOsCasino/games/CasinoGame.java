package me.bmo84.bMOsCasino.games;

import org.bukkit.entity.Player;

public abstract class CasinoGame {

    public String name;
    public String description;

    public CasinoGame(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void onGameStart(Player player) {}



}
