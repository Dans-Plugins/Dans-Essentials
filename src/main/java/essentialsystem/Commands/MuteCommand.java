package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class MuteCommand {

    Main main = null;

    public MuteCommand(Main plugin) {
        main = plugin;
    }

    public void mutePlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("me.mute") || player.hasPermission("me.admin")) {

                if (args.length > 0) {
                    if (getServer().getPlayer(args[1]) != null) {

                        if (!main.mutedPlayers.contains(args[1])) {
                            main.mutedPlayers.add(args[1]);
                            getServer().getPlayer(args[1]).sendMessage(ChatColor.RED + "You have been muted.");
                            player.sendMessage(ChatColor.GREEN + "Player has been muted.");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "That player is already muted!");
                        }

                    }
                    else {
                        player.sendMessage(ChatColor.RED + "That player isn't online!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /mute (player-name)");
                }

            }
            else {
                sender.sendMessage("Sorry! You need the 'me.mute' permission to use this command.");
            }
        }
    }

}
