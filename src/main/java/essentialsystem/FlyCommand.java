package essentialsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand {

    public void toggleFlight(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = null;
            if (args.length == 0) {
                if (!player.hasPermission("me.fly")) {
                    sender.sendMessage("Sorry! You need the 'me.fly' permission to use this command.");
                    return;
                }
                player = (Player) sender;
            }
            else {
                if (!player.hasPermission("me.fly.others")) {
                    sender.sendMessage("Sorry! You need the 'me.fly.others' permission to use this command.");
                    return;
                }
                if (Bukkit.getServer().getPlayer(args[0]).isOnline()) {
                    player = Bukkit.getServer().getPlayer(args[0]);
                    sender.sendMessage("Player's flight status toggled.");
                }
                else {
                    sender.sendMessage("That player isn't online!");
                }
            }

            player.setAllowFlight(!player.getAllowFlight());

            if (player.getAllowFlight()) {
                player.sendMessage(ChatColor.GREEN + "Flight toggled on.");
            }
            else {
                player.sendMessage(ChatColor.GREEN + "Flight toggled off.");
            }
        }
    }

}
