// Permissions:
// 'me.seen'

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
            if (getLastLogoutOfPlayer(targetPlayer) != null) {
                sender.sendMessage(targetPlayer + "has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
            }
            else {
                sender.sendMessage("That player has never logged into the server.");
            }
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.seen") || player.hasPermission("me.default")) {
                if (getLastLogoutOfPlayer(targetPlayer) != null) {
                    player.sendMessage(ChatColor.AQUA + targetPlayer + "has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
                }
                else {
                    player.sendMessage(ChatColor.RED + "That player has never logged into the server.");
                }

            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.seen' permission to use this command.");
            }
        }
    }

    String getLastLogoutOfPlayer(String playerName) {
        String lastLogout = main.getActivityRecord(playerName).getTimeSinceLastLogout();
        if (lastLogout != null) {
            return lastLogout;
        }
        else {
            return null;
        }
    }
}
