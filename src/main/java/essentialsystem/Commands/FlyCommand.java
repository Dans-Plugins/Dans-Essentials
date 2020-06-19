package essentialsystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class FlyCommand {

    public void toggleFlight(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                // toggle player's flight
                player.setAllowFlight(!player.getAllowFlight());
                if (player.getAllowFlight()) {
                    player.sendMessage(ChatColor.GREEN + "Flight enabled!");
                    return;
                }
                else {
                    player.sendMessage(ChatColor.GREEN + "Flight disabled!");
                    return;
                }
            }
            else {
                try {
                    Player target = getServer().getPlayer(args[0]);
                    target.setAllowFlight(!target.getAllowFlight());
                    if (target.getAllowFlight()) {
                        if (!player.getName().equalsIgnoreCase(target.getName())) {

                        }
                        target.sendMessage(ChatColor.GREEN + "Flight enabled!");
                        if (!player.getName().equalsIgnoreCase(target.getName())) {
                            player.sendMessage("Flight enabled for " + target.getName());
                        }

                        return;
                    }
                    else {
                        target.sendMessage(ChatColor.GREEN + "Flight disabled!");
                        if (!player.getName().equalsIgnoreCase(target.getName())) {
                            player.sendMessage("Flight disabled for " + target.getName());
                        }
                        return;
                    }
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "That player isn't online.");
                    return;
                }

            }
        }

    }

}
