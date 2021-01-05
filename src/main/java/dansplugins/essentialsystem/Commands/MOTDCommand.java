// Permissions:
// 'me.motd'

package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand {

    MedievalEssentials medievalEssentials = null;

    public MOTDCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
    }

    public void showMOTD(CommandSender sender) {
        if (!(sender instanceof Player)) {
            // console can just view MOTD anytime
            sender.sendMessage(medievalEssentials.motd.getMessage());
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.motd") || player.hasPermission("me.default")) {
                player.sendMessage(ChatColor.AQUA + medievalEssentials.motd.getMessage());
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.motd' permission to use this command.");
            }
        }

    }
}
