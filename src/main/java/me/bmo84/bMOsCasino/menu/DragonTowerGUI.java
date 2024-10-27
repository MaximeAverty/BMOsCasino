package me.bmo84.bMOsCasino.menu;

import me.bmo84.bMOsCasino.BMOsCasino;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class DragonTowerGUI {

    public DragonTowerGUI(Player player) {

        double playerBet = BMOsCasino.getPlayersBet().get(player);

        String inventoryTitle = "§aProfit: " + playerBet;

        Inventory gameInventory = Bukkit.createInventory(null, 54, "§9Profit: §c" + 0);

        for (int row = 0; row < 6; row++) {
            gameInventory.setItem(row * 9 + 3, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            gameInventory.setItem(row * 9 + 4, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            gameInventory.setItem(row * 9 + 5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        ItemStack quitGameBtn = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta quiteGameSkull = ( SkullMeta ) quitGameBtn.getItemMeta();
        if(quiteGameSkull != null) {
            quiteGameSkull.setDisplayName("§aTake profit");
            quiteGameSkull.setOwningPlayer(Bukkit.getOfflinePlayer("MrSnowDK"));
            quitGameBtn.setItemMeta(quiteGameSkull);
        }

        gameInventory.setItem(8, quitGameBtn);

        for ( int i = 0; i < gameInventory.getSize(); i++) {
            if(gameInventory.getItem(i) == null) {
                gameInventory.setItem(i, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
            }
        }


        player.openInventory(gameInventory);

    }

}
