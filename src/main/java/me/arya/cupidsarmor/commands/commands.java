package me.arya.cupidsarmor.commands;

import me.arya.cupidsarmor.CupidsArmor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("cupidsarmor.give")){
            sender.sendMessage("§cInsufficient permissions!");
            return true;
        }

        if (args.length == 2) {
            String playerName = args[0];
            String armorPart = args[1];

            final Player player = Bukkit.getPlayerExact(playerName);
            if (player == null) {
                sender.sendMessage("§cUnavailable player!");
                return true;
            }

            CupidsArmor.get().getArmorManager().giveArmor(player, armorPart);
        }

        return true;
    }
}
