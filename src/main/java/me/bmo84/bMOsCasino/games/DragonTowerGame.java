package me.bmo84.bMOsCasino.games;

import me.bmo84.bMOsCasino.BMOsCasino;
import me.bmo84.bMOsCasino.managers.DragonTowerManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

public class DragonTowerGame {

    private int currentRow = 0;

    private Player player;
    private double playerBet;
    private double playerGain = 0;
    private double multiplier = 1;
    private boolean isGameLost = false;
    private DragonTowerManager manager;

    public DragonTowerGame(Player player, DragonTowerManager manager) {
        this.player = player;
        this.playerBet = BMOsCasino.getPlayersBet().get(player);
        this.manager = manager;
    }

    public String test() {
        return player.getDisplayName();
    }

    public int getStartIndex() {
        return ( 5 - currentRow ) * 9;
    }

    public int getEndIndex() {
        return getStartIndex() + 8;
    }

    public boolean isValidRow(int slot) {
        return slot >= getStartIndex() && slot <= getEndIndex();
    }

    public void stopGame() {
        if(!getIsGameLost()) {
            BMOsCasino.getEconomy().depositPlayer(player, playerGain);
            player.sendMessage(BMOsCasino.prefix + "§fCongratulations! You just secured " + "§6" + formatGain(getNetGain()) + " §fin profit!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 1f);
            player.closeInventory();
        }else {
            setGameLost();
            player.sendMessage(BMOsCasino.prefix + "§7Oops! Better luck next time!");
            bukkitRunnable(player, 20);
        }
        manager.deletePayerGame(player);
    }

    public void setNextRow() {
        if(currentRow < 5) {
            currentRow = currentRow + 1;
        }
    }

    public void onGoodChoice() {
        calculateMultiplier();
        increaseGain();
    }

    public String formatGain(double gain) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(gain);
    }


    public void calculateMultiplier() {
        multiplier = multiplier * ((double) 3 /2);
    }

    public void setGameLost() {
        isGameLost = !isGameLost;
    }

    public boolean getIsGameLost() {
        return isGameLost;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void increaseGain() {
        playerGain += playerBet * multiplier;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public double getNetGain() {
        return playerGain - playerBet; // Pour les gains nets
    }

    public int getRandomInt() {
        return ( int ) Math.floor(Math.random() * 3) + 1;
    }

    public void bukkitRunnable(Player player, int delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory(); // Ferme l'inventaire
            }
        }.runTaskLater(BMOsCasino.getPlugin(), delay);
    }

}
