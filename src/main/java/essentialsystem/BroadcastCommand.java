package essentialsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand {
    public void broadcast(CommandSender sender, String message) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("me.broadcast")) {
                sender.sendMessage("Sorry! You need the 'me.broadcast' permission to use this command.");
                return;
            }
            sendAllPlayersMessage(message);
        }
    }

    public static void sendAllPlayersMessage(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + message);
        }
    }
}
