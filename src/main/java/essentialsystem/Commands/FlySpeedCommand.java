package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand {

    public void setFlySpeed(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("medievalessentials.flyspeed") || player.hasPermission("medievalessentials.default")) {
                // if not enough arguments
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /flyspeed (number)");
                    return;
                }

                int speed = Integer.parseInt(args[0]);

                if (speed > 0 && speed <= 10) {
                    if (player.getAllowFlight()) {
                        player.setFlySpeed(speed);
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have flight enabled!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Fly speed can only be between 1-10");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'medievalessentials.flyspeed");
            }

        }

    }

}
