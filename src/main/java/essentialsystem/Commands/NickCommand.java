package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class NickCommand {

    public void changeDisplayName(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            // permission check
            if (player.hasPermission("me.nick") || player.hasPermission("me.admin")) {

                // argument check
                if (args.length > 1) {

                    Player target = getServer().getPlayer(args[0]);

                    // online check
                    if (target != null) {

                        target.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[1]) + "&r");
                        player.sendMessage(ChatColor.GREEN + "Nickname set!");

                    }
                    else {
                        player.sendMessage(ChatColor.RED + "That player isn't online");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Usgae: /nick (player-name) (nickname)");
                }

            }

        }

    }

}
