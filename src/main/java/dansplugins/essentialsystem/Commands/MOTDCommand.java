// Permissions:
// 'me.motd'

package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.data.PersistentData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MOTDCommand {

    public void showMOTD(CommandSender sender) {
        if (!(sender instanceof Player)) {
            // console can just view MOTD anytime
            sender.sendMessage(PersistentData.getInstance().getMotd().getMessage());
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.motd") || player.hasPermission("me.default")) {
                player.sendMessage(ChatColor.AQUA + PersistentData.getInstance().getMotd().getMessage());
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.motd' permission to use this command.");
            }
        }

    }
}
