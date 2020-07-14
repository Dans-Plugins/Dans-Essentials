package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommand {

    public void setFlySpeed(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("me.flyspeed") || player.hasPermission("me.default")) {
                // if not enough arguments
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /flyspeed (number)");
                    return;
                }

                float speed = 0;
                try {
                    speed = (float) (Double.parseDouble(args[0])* 0.10);
                } catch(Exception e) {
                    player.sendMessage(ChatColor.RED + "Must be a number between 1-10");
                    return;
                }


                if (speed > 0 && speed <= 1) {
                    if (player.getAllowFlight()) {
                        player.setFlySpeed(speed);
                        player.sendMessage(ChatColor.GREEN + "Fly speed set to " + args[0]);
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
