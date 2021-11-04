// Permissions:
// 'me.fly'
// 'me.fly.others'

package dansplugins.dansessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import static org.bukkit.Bukkit.getServer;

public class FlyCommand extends AbstractCommand {

    public void toggleFlight(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                if (player.hasPermission("de.fly") || player.hasPermission("de.admin")) {
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
                    sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.fly' permission to use this command.");
                }

            }
            else {
                if (player.hasPermission("de.fly.others") || player.hasPermission("de.admin")) {
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
                else {
                    sender.sendMessage("Sorry! You need the 'me.fly.others' permission to use this command.");
                }
            }
        }

    }

}
