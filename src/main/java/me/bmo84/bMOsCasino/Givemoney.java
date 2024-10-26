package me.bmo84.bMOsCasino;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Givemoney implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            return  true;
        }

        Player player = ( Player ) sender;

        player.sendMessage("Test wirkd okygins");

        BMOsCasino.getEconomy().depositPlayer(player, 1000);
        return true;
    }
}
