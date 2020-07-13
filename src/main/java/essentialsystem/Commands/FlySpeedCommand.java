package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand {

    public void setFlySpeed(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return;
        }

        Player player = (Player) sender;

        // if its the fly speed (number) command
        if (args.length > 0 && args.length < 3) {

            if (player.hasPermission("medievalessentials.flyspeed") || player.hasPermission("medievalessentials.default")) {
                // if not enough arguments
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /flyspeed (number)");
                }

                int speed = Integer.parseInt(args[0]);

                if (player.getAllowFlight()) {
                    player.setFlySpeed(speed); // TODO: fix null error here
                }
                else {
                    player.sendMessage(ChatColor.RED + "You don't have flight enabled!");
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'medievalessentials.flyspeed");
            }

        }
    }

}
