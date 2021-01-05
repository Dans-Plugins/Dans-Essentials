package dansplugins.essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class WhoIsCommand {

    public void showIGNToPlayer(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            // permission check
            if (player.hasPermission("me.whois") || player.hasPermission("me.default")) {

                // argument check
                if (args.length > 0) {

                    boolean found = false;
                    for (Player target : getServer().getOnlinePlayers()) {
                        if (target.getDisplayName().equalsIgnoreCase(args[0])) {
                            found = true;

                            player.sendMessage(ChatColor.GREEN + "That player's IGN is " + target.getName());

                            break;
                        }
                    }

                    if (!found) {
                        player.sendMessage(ChatColor.RED + "That player wasn't found!");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /whois (name)");
                }

            }

        }

    }

}
