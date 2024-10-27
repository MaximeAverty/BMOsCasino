package me.bmo84.bMOsCasino.listeners;

import me.bmo84.bMOsCasino.BMOsCasino;
import me.bmo84.bMOsCasino.games.DragonTowerGame;
import me.bmo84.bMOsCasino.managers.DragonTowerManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class DragonTowerListener implements Listener {

    private final DragonTowerManager manager;
    private Player player;

    public DragonTowerListener() {
        this.manager = (DragonTowerManager) BMOsCasino.getCasinoGames().get("DRAGON TOWER");
    }

    @EventHandler
    public void gameEvent(InventoryClickEvent event) {
        if(!(event.getView().getTitle().startsWith("§9Profit: "))) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        DragonTowerGame game = this.manager.getPlayersGames().get(player);

        event.setCancelled(true);

        if(game == null) {
            return;
        }

        if(event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
            if(!(game.getNetGain() <= 0)) {
               game.stopGame();
            }
            return;
        }

        if(game.getCurrentRow() <= 5) {
            if(game.isValidRow(event.getSlot()) && !game.getIsGameLost()) {

                if(!(event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE)) {
                    return;
                }

                if(game.getRandomInt() > 1) {
                    game.onGoodChoice();
                    event.getView().setTitle("§9Profit: " + "§c" + game.formatGain(game.getNetGain()));
                    event.getCurrentItem().setType(Material.LIME_STAINED_GLASS_PANE);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                    if(game.getCurrentRow() == 5) {
                        game.stopGame();
                        return;
                    }
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
                    event.getCurrentItem().setType(Material.RED_STAINED_GLASS_PANE);
                    game.setGameLost();
                    game.stopGame();
                    return;
                }
                game.setNextRow();
            }
        }
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        if(!(event.getView().getTitle().startsWith("§9Profit: "))) {
            return;
        }

        if(!(event.getPlayer() instanceof Player)) {
            return;
        }

        Player player = (Player ) event.getPlayer();
        manager.deletePayerGame(player);

    }

}
