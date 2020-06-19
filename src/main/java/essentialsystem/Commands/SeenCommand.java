package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeenCommand {

    Main main = null;

    public SeenCommand(Main plugin) {
        main = plugin;
    }

    public void showLastLogout(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /seen (player-name)");
            return;
        }

        String targetPlayer = args[0];

        if (!(sender instanceof Player)) {
            // console doesn't need permission
            sender.sendMessage(targetPlayer + "has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.seen") || player.hasPermission("me.default")) {
                player.sendMessage(ChatColor.AQUA + targetPlayer + "has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.seen' permission to use this command.");
            }
        }
    }

    String getLastLogoutOfPlayer(String playerName) {
        return main.getActivityRecord(playerName).getTimeSinceLastLogout();
    }
}
