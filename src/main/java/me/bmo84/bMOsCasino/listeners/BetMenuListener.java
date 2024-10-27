package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.games.DragonTowerGame;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class BetMenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {


        if(!(event.getView().getTitle().startsWith("§9Set your bet: "))) {
            return;
        }

        event.setCancelled(true);

        Player player = ( Player) event.getWhoClicked();
        double bet;

        try {
            bet = Double.parseDouble(event.getView().getTitle().split("§c")[1]);
        } catch (NumberFormatException err) {
            Bukkit.getLogger().severe(err.toString());
            return;
        }

        if(event.getCurrentItem() == null) {
            return;
        }

        switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
            case RED_CONCRETE:
                if(bet <= 500) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 30, 33);
                    break;
                }
                bet-= 500;
                event.getView().setTitle("§9Set your bet: " + "§c" + bet);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 30, 33);
                player.sendMessage(String.valueOf(bet));
                break;

            case GREEN_CONCRETE:
                bet+= 500;
                event.getView().setTitle("§9Set your bet: " + "§c" + bet);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 30, 33);
                break;
            case  NETHER_STAR:
                break;
        }


    }

}
