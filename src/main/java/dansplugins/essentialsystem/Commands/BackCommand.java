package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand {

    MedievalEssentials medievalEssentials = null;

    public BackCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
    }

    public void teleportToLastLocation(CommandSender sender) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("me.back") || player.hasPermission("me.admin")) {

                player.teleport(medievalEssentials.lastLogins.get(player));
                player.sendMessage(ChatColor.AQUA + "Teleported to your last location!");

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'me.back'");
            }

        }

    }

}
