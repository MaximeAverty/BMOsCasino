package me.bmo84.bMOsCasino.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BetMenu {

    double bet = 500;

    public BetMenu(Player player, Material gameIcon, String gameName) {

        Inventory inv = Bukkit.createInventory(null, 27, "§9Set your bet: " + "§c" + bet);

        ItemStack lowerBet = new ItemStack(Material.RED_CONCRETE);
        ItemMeta lowerBetMeta = lowerBet.getItemMeta();
        lowerBetMeta.setDisplayName("§eLower bet");
        lowerBetMeta.setLore(List.of("§7Lower the bet by 500"));
        lowerBet.setItemMeta(lowerBetMeta);

        inv.setItem(11, lowerBet);

        ItemStack raiseBet = new ItemStack(Material.GREEN_CONCRETE);
        ItemMeta raiseBetMeta = raiseBet.getItemMeta();
        raiseBetMeta.setDisplayName("§eRaise bet");
        raiseBetMeta.setLore(List.of("§7Raise the bet by 500"));
        raiseBet.setItemMeta(raiseBetMeta);

        inv.setItem(15, raiseBet);

        ItemStack gameItem = new ItemStack(gameIcon);
        ItemMeta gameItemMeta = gameItem.getItemMeta();
        gameItemMeta.setDisplayName("§c" + gameName);
        gameItem.setItemMeta(gameItemMeta);

        inv.setItem(4, gameItem);

        ItemStack startGame = new ItemStack(Material.NETHER_STAR);
        ItemMeta startGameMeta = startGame.getItemMeta();
        startGameMeta.setDisplayName("§aStart");
        startGame.setItemMeta(startGameMeta);

        inv.setItem(13, startGame);

        for(int i = 0; i < inv.getSize(); i++) {

            if(inv.getItem(i) == null) {

                ItemStack glassPan = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                ItemMeta glassPanMeta = glassPan.getItemMeta();
                glassPanMeta.setDisplayName(ChatColor.DARK_GRAY + "");
                glassPan.setItemMeta(glassPanMeta);
                inv.setItem(i, glassPan);

            }

        }

        player.openInventory(inv);

    }

}
