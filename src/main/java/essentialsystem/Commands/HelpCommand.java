// Permissions:
// 'me.help'

package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand {

    public void sendHelpInfo(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("me.help") || player.hasPermission("me.default")) {
                sender.sendMessage(ChatColor.AQUA + "/medievalessentials help - See a list of helpful commands.");
                sender.sendMessage(ChatColor.AQUA + "/fly - Toggle flight for you or another player.");
                sender.sendMessage(ChatColor.AQUA + "/broadcast - Broadcast a message to everyone online.");
                sender.sendMessage(ChatColor.AQUA + "/motd - View the message of the day.");
                sender.sendMessage(ChatColor.AQUA + "/setmotd - Set the message of the day.");
                sender.sendMessage(ChatColor.AQUA + "/seen - See how long its been since a player has logged in.");
                sender.sendMessage(ChatColor.AQUA + "/vanish - Hide yourself from view.");
                sender.sendMessage(ChatColor.AQUA + "/mute - Hide yourself from view.");
                sender.sendMessage(ChatColor.AQUA + "/unmute - Hide yourself from view.");
            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.help' permission to use this command.");
            }
        }

    }

}
