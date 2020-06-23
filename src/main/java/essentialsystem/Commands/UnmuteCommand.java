package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class UnmuteCommand {

    Main main = null;

    public UnmuteCommand(Main plugin) {
        main = plugin;
    }

    public void unmutePlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("me.unmute") || player.hasPermission("me.admin")) {

                if (args.length > 0) {
                    if (getServer().getPlayer(args[0]) != null) {

                        if (main.mutedPlayers.contains(args[0])) {
                            main.mutedPlayers.remove(args[0]);
                            getServer().getPlayer(args[0]).sendMessage(ChatColor.RED + "You have been unmuted.");
                            player.sendMessage(ChatColor.GREEN + "Player has been unmuted.");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "That player is already not muted!");
                        }

                    }
                    else {
                        player.sendMessage(ChatColor.RED + "That player isn't online!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /unmute (player-name)");
                }

            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.unmute' permission to use this command.");
            }
        }
    }

}
