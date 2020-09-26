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
                sender.sendMessage(ChatColor.AQUA + "/nick - Give another player a nickname.");
//                sender.sendMessage(ChatColor.AQUA + "/whois - Find out a player's IGN.");
                sender.sendMessage(ChatColor.AQUA + "/getpos - Get your coordinates.");
                sender.sendMessage(ChatColor.AQUA + "/gm (number) - Set your gamemode.");
                sender.sendMessage(ChatColor.AQUA + "/label (name) - Rename an item.");
                sender.sendMessage(ChatColor.AQUA + "/invsee (player) - Interact with a player's inventory.");
                sender.sendMessage(ChatColor.AQUA + "/clearinv (player) - Clear a player's inventory.");
            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.help' permission to use this command.");
            }
        }

    }

}
