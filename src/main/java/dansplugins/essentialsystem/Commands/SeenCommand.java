// Permissions:
// 'me.seen'

package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class SeenCommand {

    MedievalEssentials medievalEssentials = null;

    public SeenCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
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
                if (!(getServer().getPlayer(targetPlayer) != null)) {
                    System.out.println(targetPlayer + " has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
                }
                else {
                    System.out.println(ChatColor.AQUA + "That player is online.");
                }

            }
            else {
                System.out.println("That player doesn't have an activity record.");
            }
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.seen") || player.hasPermission("me.default")) {
                if (getLastLogoutOfPlayer(targetPlayer) != null) {
                    if (!(getServer().getPlayer(targetPlayer) != null)) {
                        player.sendMessage(ChatColor.AQUA + targetPlayer + " has been offline for " + getLastLogoutOfPlayer(targetPlayer) + ".");
                    }
                    else {
                        player.sendMessage(ChatColor.AQUA + "That player is online.");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "That player doesn't have an activity record.");
                }

            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.seen' permission to use this command.");
            }
        }
    }

    String getLastLogoutOfPlayer(String playerName) {
        if (medievalEssentials.hasActivityRecord(playerName)) {
            String lastLogout = medievalEssentials.getActivityRecord(playerName).getTimeSinceLastLogout();
            if (lastLogout != null) {
                return lastLogout;
            }
            else {
                return "activity present but lastLogout null";
            }
        }
        return null;
    }
}
